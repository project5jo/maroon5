package com.spring.mood.projectmvc.service;

import com.spring.mood.projectmvc.dto.responseDto.ShoppingCartResponseDto;
import com.spring.mood.projectmvc.entity.ShoppingCart;
import com.spring.mood.projectmvc.mapper.ShoppingCartMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class ShoppingCartService {
    private final ShoppingCartMapper shoppingCartMapper;

    public ShoppingCartService(ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartMapper = shoppingCartMapper;
    }

    public void addToCart(Long itemId, BigDecimal itemPrice, int quantity, String userAccount) {
        log.info("addToCart 서비스 메서드 호출됨 - itemId: {}, itemPrice: {}, quantity: {}, userAccount: {}", itemId, itemPrice, quantity, userAccount);

        ShoppingCart cart = new ShoppingCart();
        cart.setShopItemId(itemId);
        cart.setCartTotalPrice(itemPrice.multiply(BigDecimal.valueOf(quantity)));
        cart.setCartTotalCount(quantity);
        cart.setUserAccount(userAccount);

        log.info("장바구니에 추가: {}", cart);

        shoppingCartMapper.save(cart);
    }

    public List<ShoppingCart> getCartByUser(String userAccount) {
        return shoppingCartMapper.findByUserAccount(userAccount);
    }
}
