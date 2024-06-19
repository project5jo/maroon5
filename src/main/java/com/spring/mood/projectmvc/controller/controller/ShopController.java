package com.spring.mood.projectmvc.controller.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ShopController {
    @GetMapping("/shop")
    public String shop(){
        System.out.println("shop 페이지 진입");
        return "html/shop-Index";
    }

}
