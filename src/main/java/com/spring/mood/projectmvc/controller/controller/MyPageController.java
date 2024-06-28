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
    private final MyPageService service;

    // MyPage 클릭했을 때 조건에 맞는경우 웹페이지 띄우기
    @GetMapping("/mypage")
    public String openMyPage (HttpSession session) {

        // 세션에서 로그인한 회원의 정보를 가져오기
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");

        if (loginUser != null) { // 로그인 했을 경우 메인페이지 띄우기
            return "html/mypage-main";
        }
        return "redirect:/sign-in"; // 로그인 안했을 경우 로그인페이지 띄우기
    }

    // 마이페이지 들어갔을 때 등록된 프로필사진이 있다면 띄우기
    @GetMapping("/mypage-memberinfo")
    public String openMyPageMemberInfo (HttpSession session, Model model) {

        // 세션에서 로그인한 회원의 정보를 가져오기
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");

        // 로그인한 회원의 정보에서 아이디 가져오기
        String account = loginUser.getAccount();

        System.out.println("로그인한아이디 = " + account);

        // 아이디를 통해 해당 회원 한 명의 정보 찾기
       RequestMyPageMemberInfoDto dto =  service.serviceFindOne(account);
       
       // JSP 로 dto 보내기
        model.addAttribute("nowMember", dto);

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


}
