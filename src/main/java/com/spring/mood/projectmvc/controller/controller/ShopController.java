package com.spring.mood.projectmvc.controller.controller;

import com.spring.mood.projectmvc.dto.responseDto.ShopItemResponseDto;
import com.spring.mood.projectmvc.entity.ShopItem;
import com.spring.mood.projectmvc.service.ShopItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ShopController {

    private final ShopItemService shopItemService;

    @GetMapping("/shop")
    public String getAllItems(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<ShopItem> items;
        if (keyword != null && !keyword.isEmpty()) {
            items = shopItemService.searchShopItems(keyword);
        } else {
            items = shopItemService.getAllItems();
        }
        model.addAttribute("items", items);
        return "html/shop-index";
    }

    @GetMapping("/shop/{id}")
    public String getItemById(@PathVariable Long id, Model model) {
        ShopItemResponseDto item = ShopItemResponseDto.fromEntity(shopItemService.getItemById(id));
        model.addAttribute("item", item);
        return "html/shop-detail";
    }
}
