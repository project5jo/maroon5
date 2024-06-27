package com.spring.mood.projectmvc.controller.controller;

import com.spring.mood.projectmvc.dto.requestDto.OrderRequestDto;
import com.spring.mood.projectmvc.entity.Orders;
import com.spring.mood.projectmvc.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // order 페이지 열기
    @GetMapping("/order")
    public String order(Model model) {
        model.addAttribute("order", new OrderRequestDto()); // jsp 로 dto 보냄
        return "order"; // order.jsp
    }


}
