package com.spring.mood.projectmvc.service;

import com.spring.mood.projectmvc.dto.responseDto.ShoppingCartResponseDto;
import com.spring.mood.projectmvc.entity.ShoppingCart;
import com.spring.mood.projectmvc.mapper.ShoppingCartMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShoppingCartService {
    private final ShoppingCartMapper shoppingCartMapper;

    @Transactional
    public void addToCart(Long itemId, BigDecimal itemPrice, int quantity, String userAccount) {
        // 사용자 계정과 아이템 ID로 기존 장바구니 아이템을 찾음
        ShoppingCart existingCart = shoppingCartMapper.findItemByUserAccountAndItemId(userAccount, itemId);

        if (existingCart != null) {
            // 기존 아이템이 있으면 수량과 총 가격을 업데이트
            Long newQuantity = existingCart.getCartTotalCount() + quantity;
            BigDecimal newTotalPrice = existingCart.getCartTotalPrice().add(itemPrice.multiply(BigDecimal.valueOf(quantity)));

            existingCart.setCartTotalCount(newQuantity);
            existingCart.setCartTotalPrice(newTotalPrice);

            boolean updated = shoppingCartMapper.update(existingCart);
            if (!updated) {
                throw new RuntimeException("장바구니 아이템을 업데이트하는 중 오류가 발생했습니다.");
            }

            log.info("업데이트된 장바구니 아이템: {}", existingCart);
        } else {
            // 기존 아이템이 없으면 새로운 아이템 추가
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUserAccount(userAccount);
            shoppingCart.setShopItemId(itemId);
            shoppingCart.setCartTotalPrice(itemPrice.multiply(BigDecimal.valueOf(quantity)));
            shoppingCart.setCartTotalCount(quantity);

            log.info("장바구니에 추가: {}", shoppingCart);

            boolean saved = shoppingCartMapper.save(shoppingCart);
            if (!saved) {
                throw new RuntimeException("장바구니에 상품을 추가하는 중 오류가 발생했습니다.");
            }
        }
    }

    public List<ShoppingCartResponseDto> getCartByUser(String userAccount) {
        return shoppingCartMapper.findByUserAccount(userAccount).stream()
                .map(ShoppingCartResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
