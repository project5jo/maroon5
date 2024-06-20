package com.spring.mood.projectmvc.controller;

import com.spring.mood.projectmvc.dto.requestDto.ShoppingCartRequestDto;
import com.spring.mood.projectmvc.dto.responseDto.OrderDetailResponseDto;
import com.spring.mood.projectmvc.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public OrderDetailResponseDto addToCart(@RequestBody ShoppingCartRequestDto shoppingCartRequestDto) {
        return shoppingCartService.addToCart(shoppingCartRequestDto);
    }

    @GetMapping("/orders")
    public List<OrderDetailResponseDto> getAllOrders() {
        return shoppingCartService.getAllOrders();
    }
}
