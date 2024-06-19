package com.spring.mood.projectmvc.controller.controller;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SignInController {
    @GetMapping("/signIn")
    public String signIn(){
        return "html/sign-in";
    }

}
