package com.spring.mood.projectmvc.util;

import javax.servlet.http.HttpSession;

public class SignInUtil {
    public static final String LoginUser ="loginUser";
    public static final String AUTO_LOGIN = "autoLogin";

    public static boolean isLoggedIn(HttpSession session){
return session.getAttribute(LoginUser)!= null;
    }
}
