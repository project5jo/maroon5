package com.spring.mood.projectmvc.service;

import com.spring.mood.projectmvc.entity.ShoppingCart;
import com.spring.mood.projectmvc.mapper.ShoppingCartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartMapper shoppingCartMapper;

    // 장바구니에 상품 추가
    @Transactional
    public void addToCart(Long itemId, BigDecimal itemPrice, int quantity, String userAccount) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserAccount(userAccount); // 사용자 계정 정보
        shoppingCart.setShopItemId(itemId); // 상품 ID
        shoppingCart.setCartTotalPrice(itemPrice.multiply(BigDecimal.valueOf(quantity))); // 총 가격 = 상품 가격 * 수량
        shoppingCart.setCartTotalCount(quantity); // 총 수량

        // 장바구니에 저장
        boolean saved = shoppingCartMapper.save(shoppingCart);
        if (!saved) {
            throw new RuntimeException("장바구니에 상품을 추가하는 중 오류가 발생했습니다.");
        }
    }

    // 기타 장바구니 관련 메서드 추가 가능
}
