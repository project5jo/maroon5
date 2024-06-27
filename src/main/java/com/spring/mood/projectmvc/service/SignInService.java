package com.spring.mood.projectmvc.service;

import com.spring.mood.projectmvc.dto.requestDto.AutoSignInDto;
import com.spring.mood.projectmvc.dto.requestDto.SignInDto;
import com.spring.mood.projectmvc.dto.responseDto.SignInUserInfoDTO;
import com.spring.mood.projectmvc.entity.Member;
import com.spring.mood.projectmvc.mapper.MemberMapper;
import com.spring.mood.projectmvc.util.SignInUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static com.spring.mood.projectmvc.service.LoginResult.*;
import static com.spring.mood.projectmvc.util.SignInUtil.AUTO_LOGIN;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignInService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder encoder;



    //로그인 검증 처리
//, HttpSession session, HttpServletResponse response
    public LoginResult authenticate(SignInDto dto , HttpSession session, HttpServletResponse response){

        //회원가입 유무 확인
        String account = dto.getAccount();
        Member foundMember = memberMapper.findOne(account);

        if(foundMember == null){
            log.info("{} - 회원가입 필요합니다", account);
            return NO_ACC;
        }

        //비밀번호 확인 결과
        String inputPassword = dto.getPassword();//찾은 비번
        String userPassword = foundMember.getUserPassword();//데이터베이스에 저장된 비번
        log.info("inputPassword:{}",inputPassword);
        log.info("userPassword:{}",userPassword);

//        if(!encoder.matches(inputPassword, userPassword))
//        if(!inputPassword.equals(userPassword))
        if(!encoder.matches(inputPassword, userPassword)){
            log.info("비밀번호가 일치하지 않습니다");
            return NO_PW;
        }

        //자동 로그인
        if(dto.isAutoLogin()){
            Cookie autoLogin = new Cookie(AUTO_LOGIN, session.getId());
            autoLogin.setPath("/");
            autoLogin.setMaxAge(60 * 60 * 24 * 90);
            response.addCookie(autoLogin);
            memberMapper.updateAutoLogin(AutoSignInDto.builder()
                    .sessionId(session.getId())
                    .account(account)
                    .limitTime(LocalDateTime.now().plusDays(90))
                    .build());

        }

        maintainLoginState(session, foundMember);
        // Spring Security Authentication 설정 추가 시작
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + foundMember.getUserRole()));
        Authentication authentication = new UsernamePasswordAuthenticationToken(foundMember.getUserAccount(), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Spring Security Authentication 설정 추가 끝

        // 인증 정보 로그 추가 시작
        log.info("Authenticated user: " + authentication.getName());
        authentication.getAuthorities().forEach(authority -> log.info("Authority: " + authority.getAuthority()));
        // 인증 정보 로그 추가 끝

        return SUCCESS;
    }

    public static void maintainLoginState(HttpSession session, Member foundmember){
//        세션의 수명
        session.setMaxInactiveInterval(60 * 60);
        //사용자 정보 기억
        session.setAttribute("loginUser",new SignInUserInfoDTO(foundmember));
        log.info("{}님 로그인 성공",foundmember.getUserName());
    }

    public void autoLoginClear(HttpServletRequest request, HttpServletResponse response) {
        // 1. 쿠키 제거하기
        Cookie cookie = WebUtils.getCookie(request, AUTO_LOGIN);
        if (cookie != null) {
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        memberMapper.updateAutoLogin(
                AutoSignInDto.builder()
                        .limitTime(LocalDateTime.now())
                        .sessionId("none")
                        .account(SignInUtil.getLoggedInUserAccount(request.getSession()))
                        .build()
        );
    }
}