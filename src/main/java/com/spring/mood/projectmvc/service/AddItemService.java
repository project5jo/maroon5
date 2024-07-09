package com.spring.mood.projectmvc.service;

import com.spring.mood.projectmvc.entity.ShopItem;
import com.spring.mood.projectmvc.mapper.ShopItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AddItemService {

    private final ShopItemMapper shopItemMapper;

    public void addItem(ShopItem shopItem) {
        if (shopItem.getShopItemView() == null) {
            shopItem.setShopItemView(0L);
        }
        shopItemMapper.save(shopItem);
    }
}



