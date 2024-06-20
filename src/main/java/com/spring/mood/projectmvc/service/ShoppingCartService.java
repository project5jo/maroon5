package com.spring.mood.projectmvc.service;

import com.spring.mood.projectmvc.dto.responseDto.ShopDetailResponseDTO;
import com.spring.mood.projectmvc.entity.ShopItem;
import com.spring.mood.projectmvc.entity.ShoppingCart;
import com.spring.mood.projectmvc.mapper.OrderDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private final OrderDetailMapper orderDetailMapper;

    // 수정중
//
//
//    public void addToCart(ShopDetailResponseDTO cartDTO) {
//        ShoppingCart shoppingCart = new ShoppingCart();
//        shoppingCart.setUserAccount(cartDTO.getUserAccount());
//        shoppingCart.setShopItemId(cartDTO.getShopItemId());
//        shoppingCart.setCartTotalCount(cartDTO.getCartTotalCount());
//
//        // 상품 가격 조회 후 총 금액 계산
//        ShopItem item = shoppingCartMapper.findShopItemById(cartDTO.getShopItemId());
//        int totalPrice = item.getItemPrice() * cartDTO.getCartTotalCount();
//        shoppingCart.setCartTotalPrice(totalPrice);
//
//        shoppingCartMapper.insertShoppingCart(shoppingCart);
//    }
}