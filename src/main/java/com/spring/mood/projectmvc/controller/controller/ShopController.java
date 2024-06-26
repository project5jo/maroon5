package com.spring.mood.projectmvc.controller.controller;

import com.spring.mood.projectmvc.dto.responseDto.ShopItemResponseDto;
import com.spring.mood.projectmvc.entity.ShopItem;
import com.spring.mood.projectmvc.service.AddItemService;
import com.spring.mood.projectmvc.service.ShopItemService;
import com.spring.mood.projectmvc.service.ShoppingCartService;
import com.spring.mood.projectmvc.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ShopController {

    private final ShopItemService shopItemService;
    private final AddItemService addItemService;
    private final ShoppingCartService shoppingCartService;

    @GetMapping("/shop")
    public String getAllItems(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<ShopItem> items;
        if (keyword != null && !keyword.isEmpty()) {
            items = shopItemService.searchShopItems(keyword);
        } else {
            items = shopItemService.getAllItems();
        }

        // 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userRole = "guest"; // 기본값을 guest로 설정
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            System.out.println("Authentication: " + authentication);
            authentication.getAuthorities().forEach(authority -> System.out.println("Authority: " + authority.getAuthority()));

            userRole = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .filter(role -> role.equals("ROLE_admin") || role.equals("ROLE_user")) // ROLE_admin 또는 ROLE_user로 비교
                    .findFirst()
                    .orElse("guest");
        }

        model.addAttribute("items", items);
        model.addAttribute("userRole", userRole); // 사용자 역할을 모델에 추가

        // Debugging: userRole 값 출력
        System.out.println("User Role: " + userRole);

        return "html/shop-Index";
    }

    @GetMapping("/shop/{id}")
    public String getItemById(@PathVariable Long id, Model model) {
        ShopItemResponseDto item = ShopItemResponseDto.fromEntity(shopItemService.getItemById(id));
        model.addAttribute("item", item);
        return "html/shop-detail";
    }

    @GetMapping("/shop/add")
    public String showAddItemPage() {
        return "html/shop-addItem";  //
    }

    private String rootPath = "/Users/jeongjaehan/Desktop/Developer/upload";

    @PostMapping("/shop/add")
    public String uploadFile(
            @RequestParam("shop_item_name") String name,
            @RequestParam("shop_item_desc") String description,
            @RequestParam("shop_item_price") Double price,
            @RequestParam("shop_item_stock") Long stock,
            @RequestParam("shop_item_img") MultipartFile file,
            HttpSession session,
            RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
        log.info("file-name: {}", file.getOriginalFilename());
        log.info("file-size: {}MB", file.getSize() / 1024.0 / 1024.0);
        log.info("file-type: {}", file.getContentType());

        File root = new File(rootPath);
        if (!root.exists()) root.mkdirs();

        String filePath = FileUtil.uploadFile(rootPath, file);
        log.info("File saved at: {}", filePath);

        ShopItem shopItem = ShopItem.builder()
                .shopItemName(name)
                .shopItemDesc(description)
                .shopItemPrice(price)
                .shopItemStock(stock)
                .shopItemImg(filePath)
                .shopItemDate(LocalDateTime.now())
                .shopItemView(0L)
                .build();

        addItemService.addItem(shopItem);

        return "redirect:/shop/add";
    }

    @GetMapping("/shop/delete/{itemId}")
    public String deleteItem(@PathVariable("itemId") String itemId, RedirectAttributes redirectAttributes) {
        shopItemService.deleteItem(Long.valueOf(itemId));  // itemService에서 실제 삭제 처리
        redirectAttributes.addFlashAttribute("message", "상품이 삭제되었습니다.");
        return "redirect:/shop";
    }



    @PostMapping("/addToCart")
    public String addToCart(@RequestParam("itemId") Long itemId,
                            @RequestParam("itemPrice") BigDecimal itemPrice,
                            @RequestParam("quantity") int quantity,
                            RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            redirectAttributes.addFlashAttribute("errorMessage", "로그인 후 이용 바랍니다.");
            return "redirect:/shop/" + itemId;
        }

        String userAccount = authentication.getName(); // 현재 로그인된 사용자 계정 가져오기

        // 로그 추가
        log.info("장바구니에 추가 요청 수신: itemId={}, itemPrice={}, quantity={}, userAccount={}",
                itemId, itemPrice, quantity, userAccount);

        try {
            shoppingCartService.addToCart(itemId, itemPrice, quantity, userAccount);
            redirectAttributes.addFlashAttribute("successMessage", "장바구니에 상품이 담겼습니다!");
        } catch (Exception e) {
            log.error("장바구니 추가 중 오류 발생", e);
            redirectAttributes.addFlashAttribute("errorMessage", "장바구니에 상품을 추가하는 중 오류가 발생했습니다.");
        }
        return "redirect:/shop/" + itemId;
    }

}
