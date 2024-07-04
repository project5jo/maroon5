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
        ShoppingCart existingCart = shoppingCartMapper.findItemByUserAccountAndItemId(userAccount, itemId);

        if (existingCart != null) {
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

    @Transactional
    public void updateCartItem(String userAccount, Long itemId, int quantity, BigDecimal totalPrice) {
        ShoppingCart existingCart = shoppingCartMapper.findItemByUserAccountAndItemId(userAccount, itemId);

        if (existingCart != null) {
            existingCart.setCartTotalCount(quantity);
            existingCart.setCartTotalPrice(totalPrice);

            boolean updated = shoppingCartMapper.update(existingCart);
            if (!updated) {
                throw new RuntimeException("장바구니 아이템을 업데이트하는 중 오류가 발생했습니다.");
            }

            log.info("업데이트된 장바구니 아이템: {}", existingCart);
        } else {
            throw new RuntimeException("장바구니 아이템이 존재하지 않습니다.");
        }
    }

    public List<ShoppingCartResponseDto> getCartByUser(String userAccount) {
        return shoppingCartMapper.findByUserAccount(userAccount).stream()
                .map(ShoppingCartResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public void removeFromCart(Long itemId, String userAccount) {
        ShoppingCart existingCart = shoppingCartMapper.findItemByUserAccountAndItemId(userAccount, itemId);
        if (existingCart != null) {
            boolean deleted = shoppingCartMapper.delete(existingCart.getCartId());
            if (!deleted) {
                throw new RuntimeException("장바구니 아이템을 제거하는 중 오류가 발생했습니다.");
            }
            log.info("제거된 장바구니 아이템: {}", existingCart);
        } else {
            throw new RuntimeException("제거할 장바구니 아이템이 존재하지 않습니다.");
        }
    }
}
