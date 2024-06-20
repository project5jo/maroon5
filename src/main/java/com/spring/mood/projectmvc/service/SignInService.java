package com.spring.mood.projectmvc.service;

import com.spring.mood.projectmvc.dto.requestDto.SignInDto;
import com.spring.mood.projectmvc.dto.responseDto.SignInUserInfoDTO;
import com.spring.mood.projectmvc.entity.Member;
import com.spring.mood.projectmvc.mapper.MemberMapper;
import com.spring.mood.projectmvc.util.SignInUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        if(!inputPassword.equals(userPassword)){
            log.info("비밀번호가 일치하지 않습니다");
            return NO_PW;
        }

        //자동 로그인
        if(dto.isAutoLogin()){
            Cookie autoLogin = new Cookie(AUTO_LOGIN, session.getId());
            autoLogin.setPath("/");
            autoLogin.setMaxAge(60 * 60 * 24 * 90);
            response.addCookie(autoLogin);

        }

//        maintainLoginState(session, foundMember);
//        세션의 수명
        session.setMaxInactiveInterval(60 * 60);
        //사용자 정보 기억
        session.setAttribute("loginUser",new SignInUserInfoDTO(foundMember));
        return SUCCESS;
    }

//    public static void maintainLoginState(HttpSession session, Member foundmember){
//
//        log.info("{}님 로그인 성공",foundmember.getUserName());
//    }

}
