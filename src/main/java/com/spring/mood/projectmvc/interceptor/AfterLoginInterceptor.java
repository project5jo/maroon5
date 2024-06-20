package com.spring.mood.projectmvc.interceptor;

import com.spring.mood.projectmvc.util.SignInUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@Slf4j
public class AfterLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

       if (SignInUtil.isLoggedIn(request.getSession())) {
           response.sendRedirect("/");
           return false;
       }
        return true;
    }

}
