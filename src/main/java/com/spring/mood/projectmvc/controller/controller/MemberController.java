package com.spring.mood.projectmvc.controller.controller;

import com.spring.mood.projectmvc.dto.requestDto.RequestMemberDto;
import com.spring.mood.projectmvc.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    
    private final MemberService memberService;

    // 회원가입 페이지 열기
    @GetMapping("/create")
    public String openCreate () {
        return "html/sign-up";
    }

    // 회원가입 입력정보 저장
    @PostMapping("/create")
    public String inputMember (@Validated RequestMemberDto dto) {

        System.out.println("dto = " + dto);
        // account=뽀로로&password=1234&email=aa@gmail.com&birth=2024-06-10

        boolean flag = memberService.memberServiceSave(dto);

        return flag ? "redirect:/login" : "redirect:/create";
    }

    // 아이디, 이메일 중복검사 비동기 요청 처리
    @PostMapping("/checkid")
    @ResponseBody
    public ResponseEntity<Boolean> checkId (@RequestParam("account")String account) {
//        log.info("account: " + account);
        boolean flag = false; // 아이디 중복 체크

        if (memberService.serviceCheckId(account)) {
            // 입력값과 동일한 저장사항을 찾을 수 있는 경우
            flag = true;
        } else {
            // 찾을 수 없는 경우
            flag = false;
        }
        return new ResponseEntity<>(flag, HttpStatus.OK);
    }

    // 아이디, 이메일 중복검사 비동기 요청 처리
    @PostMapping("/checkemail")
    @ResponseBody
    public ResponseEntity<Boolean> checkEmail (@RequestParam("email")String email) {
        log.info("email: " + email);
        boolean flag = false; // 아이디 중복 체크

        if (memberService.serviceCheckEmail(email)) {
            // 입력값과 동일한 저장사항을 찾을 수 있는 경우
            flag = true;
        } else {
            // 찾을 수 없는 경우
            flag = false;
        }
        return new ResponseEntity<>(flag, HttpStatus.OK);
    }

    // 로그인 페이지 열기
    @GetMapping("/login")
    public String openLogin () {
        return "html/sign-in";
    }

}
