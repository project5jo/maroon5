package com.spring.mood.projectmvc.interceptor;

import com.spring.mood.projectmvc.entity.Member;
import com.spring.mood.projectmvc.mapper.MemberMapper;
import com.spring.mood.projectmvc.service.MemberService;
import com.spring.mood.projectmvc.service.SignInService;
import com.spring.mood.projectmvc.util.SignInUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class AutoLoginInterceptor implements HandlerInterceptor {

    private final MemberMapper memberMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
     Cookie autoSignInCookie  = WebUtils.getCookie(request, SignInUtil.AUTO_LOGIN);

             if(autoSignInCookie != null && !SignInUtil.isLoggedIn(request.getSession())){
                 String sessionId = autoSignInCookie.getValue();
                 Member foundMember = memberMapper.findUserBySessionId(sessionId);
                 if(foundMember != null && LocalDateTime.now().isBefore(foundMember.getLimitTime())){
                     SignInService.maintainLoginState(request.getSession(), foundMember);
                 }

             }
        return true;
    }
}
