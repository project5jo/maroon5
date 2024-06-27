package com.spring.mood.projectmvc.util;

import com.spring.mood.projectmvc.dto.responseDto.SignInUserInfoDTO;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignInUtil {
    public static final String LoginUser ="loginUser";
    public static final String AUTO_LOGIN = "autoLogin";

    //로그인 여부 확인
    public static boolean isLoggedIn(HttpSession session){
        return session.getAttribute(LoginUser)!= null;
    }

    public static boolean isAutoLoggedIn(HttpServletRequest request) {
        Cookie autoSignInCookie = WebUtils.getCookie(request,AUTO_LOGIN);
        return autoSignInCookie != null;
    }

    // 로그인한 회원의 계정명 얻기
    public static String getLoggedInUserAccount(HttpSession session) {
        SignInUserInfoDTO loggedInUser = getLoggedInUser(session);
        return loggedInUser != null ? loggedInUser.getAccount() : null;
    }

    public static SignInUserInfoDTO getLoggedInUser(HttpSession session) {
        return (SignInUserInfoDTO) session.getAttribute(LoginUser);
    }
}