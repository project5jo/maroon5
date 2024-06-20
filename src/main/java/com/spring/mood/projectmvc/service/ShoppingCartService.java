package com.spring.mood.projectmvc.service;

import com.spring.mood.projectmvc.dto.requestDto.ShoppingCartRequestDto;
import com.spring.mood.projectmvc.dto.responseDto.OrderDetailResponseDto;
import com.spring.mood.projectmvc.entity.Orders;
import com.spring.mood.projectmvc.entity.ShoppingCart;
import com.spring.mood.projectmvc.mapper.ShoppingCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService {

    @Autowired

    public OrderDetailResponseDto addToCart(ShoppingCartRequestDto shoppingCartRequestDto) {
        Orders order = Orders.builder()
                .userAccount(shoppingCartRequestDto.getUserAccount())
                .orderDate(java.time.LocalDateTime.now())
                .build();
        Orders savedOrder = ordersRepository.save(order);
        return toResponseDto(savedOrder);
    }

    public List<OrderDetailResponseDto> getAllOrders() {
        return ShoppingCartMapper.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    private OrderDetailResponseDto toResponseDto(Orders order) {
        return OrderDetailResponseDto.builder()
                .orderId(order.getOrderId())
                .userAccount(order.getUserAccount())
                .orderDetailCount(order.)
                .orderDetailStatus("Pending")
                .build();
    }
}
