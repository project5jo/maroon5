package com.spring.mood.projectmvc.controller.controller;

import com.spring.mood.projectmvc.dto.requestDto.PaymentDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PaymentController {
    private static final Logger log = LoggerFactory.getLogger(SignInController.class);

    @GetMapping("/payment")
    public String payment(){
        log.info("/payment get");
        return "html/payment";
    }

    @PostMapping("/payment")
    public String payment(PaymentDto dto){
        return "html/payment";
    }




}
