package com.spring.mood.projectmvc.controller.controller;

import com.spring.mood.projectmvc.dto.requestDto.RequestMyPageMemberInfoDto;
import com.spring.mood.projectmvc.dto.responseDto.SignInUserInfoDTO;
import com.spring.mood.projectmvc.entity.Member;
import com.spring.mood.projectmvc.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MyPageController {

    private static final Logger log = LoggerFactory.getLogger(MyPageController.class);
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
    public String openMyPageMemberInfo (HttpSession session, Model model) {

        // 세션에서 로그인한 회원의 정보를 가져오기
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");
        // 로그인한 회원의 정보에서 아이디 가져오기
        String account = loginUser.getAccount();
//
////        // 아이디를 통해 해당 회원 한 명의 정보 찾기
//        RequestMyPageMemberInfoDto thisMember = service.serviceFindOne(account);
//
//        Member member = membermapper.findOne(account);

//        String profile = member.getUserProfile();

        // 회원의 정보를 JSP 로 보내기
//        model.addAttribute("thisMember", thisMember);

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
