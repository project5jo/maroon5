package com.spring.mood.projectmvc.controller.controller;

import com.spring.mood.projectmvc.dto.requestDto.RequestMemberDto;
import com.spring.mood.projectmvc.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MemberController {
    
    private final MemberService memberService;

    // 회원가입 페이지 열기
    @GetMapping("/create")
    public String openCreate () {
        return "html/sign-up";
    }

    // 회원가입 입력정보 저장
    @PostMapping("/createe")
    public String inputMember (@Validated RequestMemberDto dto) {

//        System.out.println("dto = " + dto);
        // account=뽀로로&password=1234&email=aa@gmail.com&birth=2024-06-10

        boolean flag = memberService.memberServiceSave(dto);

        return flag ? "redirect:/login" : "redirect:/create";
    }

    // // 회원가입시 이름 & 이메일 중복확인
    @ResponseBody
    @PostMapping("/create")
    public int checkId (RequestMemberDto dto, String userAccount) {

        int checkId = memberService.ServiceCheckId(userAccount);

        System.out.println("checkId = " + checkId);

        return checkId;
    }

    // 로그인 페이지 열기
    @GetMapping("/login")
    public String openLogin () {
        return "html/sign-in";
    }

}
