package com.spring.mood.projectmvc.controller.controller;

import com.spring.mood.projectmvc.dto.requestDto.RequestMyPageMemberInfoDto;
import com.spring.mood.projectmvc.dto.responseDto.SignInUserInfoDTO;
import com.spring.mood.projectmvc.entity.Member;
import com.spring.mood.projectmvc.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MyPageController {

    MyPageService service;

    // MyPage 클릭했을 때 조건에 맞는경우 웹페이지 띄우기
    @GetMapping("/mypage")
    public String openMyPage (RequestMyPageMemberInfoDto dto, HttpSession session) {

        // 세션에서 로그인한 회원의 정보를 가져오기
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");

        if (loginUser != null) {
            return "html/mypage-main";
        }
        return "redirect:/sign-in";
    }

    @GetMapping("/mypage-memberinfo")
    public String openMyPageMemberInfo () {
        return "html/mypage-memberInfo";
    }

    @GetMapping("/mypage-password")
    public String openMyPagePassword () {
        return "html/mypage-password";
    }

    @GetMapping("/mypage-cancel")
    public String openMyPageCancel () {
        return "html/mypage-cancel";
    }

    // MyPage 클릭했을 때 조건에 맞는경우 웹페이지 띄우기

//    // MyPage 회원정보 수정
//    @PostMapping("/memberInfo")
//    public String modifyMemberInfo () {
//
//        service.
//
//        return "html/mypage-memberInfo";
//    }

}
