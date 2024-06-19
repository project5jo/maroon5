package com.spring.mood.projectmvc.controller.controller;

import com.spring.mood.projectmvc.dto.requestDto.SignInDto;
import com.spring.mood.projectmvc.service.LoginResult;
import com.spring.mood.projectmvc.service.SignInService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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
                         HttpServletRequest request,
                         HttpServletResponse response) {
        //세션 얻기
        HttpSession session = request.getSession();
        LoginResult result = service.authenticate(dto);

        redirectAttributes.addFlashAttribute("result", result);

        if (result == SUCCESS) {

            String redirect = (String) session.getAttribute("redirect");
            if (redirect != null) {
                session.removeAttribute("redirect");
                return "redirect" + redirect;
            }
            log.info("로그인 성공!!!");
            return "redirect:/";
        }
        return "redirect:/";
    }

}
