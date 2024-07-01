package com.spring.mood.projectmvc.service;

import com.spring.mood.projectmvc.dto.responseDto.ShoppingCartResponseDto;
import com.spring.mood.projectmvc.entity.Orders;
import com.spring.mood.projectmvc.mapper.OrderMapper;
import com.spring.mood.projectmvc.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;
    private final ShoppingCartService shoppingCartService;

    @Transactional
    public void createOrder(Orders order) {
        orderMapper.saveOrder(order);
    }

    public BigDecimal calculateTotalPrice(String userAccount) {
        List<ShoppingCartResponseDto> cartItems = shoppingCartService.getCartByUser(userAccount);
        return cartItems.stream()
                .map(ShoppingCartResponseDto::getCartTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
