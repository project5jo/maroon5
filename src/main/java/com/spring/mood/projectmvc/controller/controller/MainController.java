package com.spring.mood.projectmvc.controller.controller;


import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class MainController {
    @GetMapping("/index")
    public String indexPage(@RequestParam int topicId, @RequestParam int roomId, Model model) {
        System.out.println("topicId = " + topicId);
        model.addAttribute("topicId", topicId);
        model.addAttribute("roomId", roomId);
        return "index";
    }


    @GetMapping("/zzz")
    public String indexPage() {
        System.out.println("zz");
        return "html/admin";
    }
}


