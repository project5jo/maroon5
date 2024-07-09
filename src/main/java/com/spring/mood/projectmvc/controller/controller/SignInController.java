package com.spring.mood.projectmvc.controller.controller;

import com.spring.mood.projectmvc.dto.requestDto.FindIdDto;
import com.spring.mood.projectmvc.dto.requestDto.ModifyPwDto;
import com.spring.mood.projectmvc.dto.requestDto.SignInDto;
import com.spring.mood.projectmvc.entity.Member;
import com.spring.mood.projectmvc.service.LoginResult;
import com.spring.mood.projectmvc.service.MemberService;
import com.spring.mood.projectmvc.service.SignInService;
import com.spring.mood.projectmvc.util.SignInUtil;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.spring.mood.projectmvc.service.LoginResult.SUCCESS;

@Controller
@RequiredArgsConstructor
public class SignInController {


    private final SignInService service;

    private static final Logger log = LoggerFactory.getLogger(SignInController.class);

    //  로그인 양식 열기
    @GetMapping("/sign-in")
    public String signIn(HttpSession session, @RequestParam(required = false) String redirect) {

        session.setAttribute("redirect", redirect);
        log.info("html/sign-in GET : forwarding to sign-in.jsp");

        return "html/sign-in";
    }

    @PostMapping("/sign-in")
    public String signIn(SignInDto dto,
                         RedirectAttributes redirectAttributes,
                         //요청
                         HttpServletRequest request,
                         //요청에 대한 응답
                         HttpServletResponse response) {
        log.info("html/sign-in Post");
        log.debug("dto:{}", dto);

        //세션 얻기(사용자를 기억해줌)
        HttpSession session = request.getSession();
        LoginResult result = service.authenticate(dto, session, response);
        //, session,response
        // 사용자 정보 및 권한 확인 로그 추가 시작
        if (result == SUCCESS) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            log.info("Authentication: " + authentication);
            authentication.getAuthorities().forEach(authority -> log.info("Authority: " + authority.getAuthority()));
        }
        // 사용자 정보 및 권한 확인 로그 추가 끝

        redirectAttributes.addFlashAttribute("result", result);

        if (result == SUCCESS) {
            String redirect = (String) session.getAttribute("redirect");
            if (redirect != null) {
                session.removeAttribute("redirect");
                return "redirect" + redirect;
            }
            return "redirect:/";
        }
        return "redirect:/sign-in";
    }

    //아이디 찾기
    @GetMapping("/findId")
    public String findId() {
        log.info("html/findId Get");
        return "html/find-id";
    }

    @PostMapping("/findId")
    public String findId(FindIdDto dto, Model model,RedirectAttributes redirectAttributes) {

        Member user = service.findUser(dto);
        System.out.println("controller에서 유저 정보 = " + user);
        if (user != null) {
            model.addAttribute("user", user);
            return "html/find-id";
        }
//        redirect:/sign-in
//        redirect:/findId

        redirectAttributes.addFlashAttribute("result","null");
        return "redirect:/findId";
    }

    //비밀번호 수정 페이지
    @GetMapping("/modifyPw")
    public String modifyPw(){
        return "html/modify-pw";
    }

    @PostMapping("/modifyPw")
    public String modifyPw(ModifyPwDto dto, RedirectAttributes redirectAttributes){
        log.info("html/modifyPw Post");
        log.debug("dto:{}", dto);

        boolean flag = service.modifyPw(dto);

        if(!flag){
            redirectAttributes.addFlashAttribute("result","null");
            return "redirect:/modifyPw";
        }

        return "redirect:/sign-in";
    }


    //    로그아웃
    @GetMapping("/sign-out")
    public String signOut(HttpServletRequest request, HttpServletResponse response) {

//        자동 로그인
        if (SignInUtil.isAutoLoggedIn(request)) {
            // 쿠키를 제거하고, DB에도 자동로그인 관련데이터를 원래대로 해놓음
            service.autoLoginClear(request, response);
        }
        HttpSession session = request.getSession();
        session.removeAttribute("loginUser");
        session.invalidate();
        return "redirect:/";
    }


}
