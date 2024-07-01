package com.spring.mood.projectmvc.controller.controller;

import com.spring.mood.projectmvc.dto.requestDto.OrderRequestDto;
import com.spring.mood.projectmvc.dto.responseDto.OrderResponseDto;
import com.spring.mood.projectmvc.dto.responseDto.ShoppingCartResponseDto;
import com.spring.mood.projectmvc.entity.Orders;
import com.spring.mood.projectmvc.entity.User;
import com.spring.mood.projectmvc.service.OrderService;
import com.spring.mood.projectmvc.service.ShoppingCartService;
import com.spring.mood.projectmvc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

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
        BigDecimal totalPrice = orderService.calculateTotalPrice(orderRequestDto.getUserAccount());
        User user = userService.getUserByAccount(orderRequestDto.getUserAccount());

        model.addAttribute("order", orderResponseDto);
        model.addAttribute("cartItems", shoppingCartService.getCartByUser(orderRequestDto.getUserAccount()));
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("userPoint", user.getUserPoint());

        return "html/payment";
    }
}
