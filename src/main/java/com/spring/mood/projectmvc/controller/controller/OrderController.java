package com.spring.mood.projectmvc.controller.controller;

import com.spring.mood.projectmvc.dto.requestDto.OrderRequestDto;
import com.spring.mood.projectmvc.dto.responseDto.OrderResponseDto;
import com.spring.mood.projectmvc.entity.Orders;
import com.spring.mood.projectmvc.service.OrderService;
import com.spring.mood.projectmvc.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;

    @PostMapping("/checkout")
    public String checkout(OrderRequestDto orderRequestDto, Model model) {
        Orders order = Orders.builder()
                .userAccount(orderRequestDto.getUserAccount())
                .orderDate(LocalDateTime.now())
                .address1(orderRequestDto.getAddress1())
                .address2(orderRequestDto.getAddress2())
                .address3(orderRequestDto.getAddress3())
                .receiverName(orderRequestDto.getReceiverName())
                .receiverPhone(orderRequestDto.getReceiverPhone())
                .build();

        orderService.createOrder(order);

        OrderResponseDto orderResponseDto = OrderResponseDto.fromEntity(order);

        model.addAttribute("order", orderResponseDto);
        model.addAttribute("cartItems", shoppingCartService.getCartByUser(orderRequestDto.getUserAccount()));

        return "html/payment";
    }
}
