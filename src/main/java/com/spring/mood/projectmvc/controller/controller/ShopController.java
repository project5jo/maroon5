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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

        return "html/shop-index";
    }

    @GetMapping("/shop/{id}")
    public String getItemById(@PathVariable Long id, Model model) {
        ShopItem itemById = shopItemService.getItemById(id);
        System.out.println("itemById = " + itemById);
        ShopItemResponseDto item = ShopItemResponseDto.fromEntity(itemById);
        System.out.println("iteㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇm = " + item);
        model.addAttribute("item", item);

        // 랜덤으로 세 개의 이미지를 선택하여 모델에 추가
        List<String> randomImages = getRandomImages();
        model.addAttribute("randomImages", randomImages);

        return "html/shop-detail";
    }

    @GetMapping("/shop/add")
    public String showAddItemPage() {
        return "html/shop-addItem";  //
    }

    private String rootPath = "/Users/jehoon/spring-prj/upload";

    @PostMapping("/shop/add")
    public String uploadFile(
            @RequestParam("shop_item_name") String name,
            @RequestParam("shop_item_desc") String description,
            @RequestParam("shop_item_price") BigDecimal price,
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

    private List<String> getRandomImages() {
        List<ShopItem> allItems = shopItemService.getAllItems();
        List<String> allImages = allItems.stream()
                .map(ShopItem::getShopItemImg)
                .collect(Collectors.toList());

        Collections.shuffle(allImages);
        return allImages.stream().limit(3).collect(Collectors.toList());
    }
}