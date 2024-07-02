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

    private static final String UPLOAD_DIR = "/Users/jehoon/spring-prj/upload";

    @GetMapping("/shop")
    public String getAllItems(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<ShopItem> items;
        if (keyword != null && !keyword.isEmpty()) {
            items = shopItemService.searchShopItems(keyword);
        } else {
            items = shopItemService.getAllItems();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userRole = "guest";
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            userRole = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .filter(role -> role.equals("ROLE_admin") || role.equals("ROLE_user"))
                    .findFirst()
                    .orElse("guest");
        }

        model.addAttribute("items", items);
        model.addAttribute("userRole", userRole);

        return "html/shop-index";
    }

    @GetMapping("/shop/{id}")
    public String getItemById(@PathVariable Long id, Model model) {
        ShopItem itemById = shopItemService.getItemById(id);
        System.out.println("itemById = " + itemById);
        ShopItemResponseDto item = ShopItemResponseDto.fromEntity(itemById);
        System.out.println("iteㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇm = " + item);
        model.addAttribute("item", item);

        List<ShopItem> randomImages = getRandomImages();
        model.addAttribute("randomImages", randomImages);

        return "html/shop-detail";
    }


    @GetMapping("/shop/add")
    public String showAddItemPage() {
        return "html/shop-addItem";
    }


    private String rootPath = "/Users/superstar/spring-prj/upload/";

    @PostMapping("/shop/add")
    public String uploadFile(
            @RequestParam("shop_item_name") String name,
            @RequestParam("shop_item_desc") String description,
            @RequestParam("shop_item_price") BigDecimal price,
            @RequestParam("shop_item_stock") Long stock,
            @RequestParam("shop_item_img") MultipartFile file,
            RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
        log.info("file-name: {}", file.getOriginalFilename());
        log.info("file-size: {}MB", file.getSize() / 1024.0 / 1024.0);
        log.info("file-type: {}", file.getContentType());

        String fileUrl = FileUtil.uploadFile(UPLOAD_DIR, file);
        log.info("File saved at: {}", fileUrl);

        ShopItem shopItem = ShopItem.builder()
                .shopItemName(name)
                .shopItemDesc(description)
                .shopItemPrice(price)
                .shopItemStock(stock)
                .shopItemImg(fileUrl)
                .shopItemDate(LocalDateTime.now())
                .shopItemView(0L)
                .build();

        addItemService.addItem(shopItem);

        return "redirect:/shop";
    }

    @GetMapping("/shop/delete/{itemId}")
    public String deleteItem(@PathVariable("itemId") String itemId, RedirectAttributes redirectAttributes) {
        shopItemService.deleteItem(Long.valueOf(itemId));
        redirectAttributes.addFlashAttribute("message", "상품이 삭제되었습니다.");
        return "redirect:/shop";
    }

    private List<ShopItem> getRandomImages() {
        List<ShopItem> allItems = shopItemService.getAllItems();
        Collections.shuffle(allItems);
        return allItems.stream().limit(3).collect(Collectors.toList());
    }
}
