package com.spring.mood.projectmvc.service;

import com.spring.mood.projectmvc.dto.requestDto.SignInDto;
import com.spring.mood.projectmvc.mapper.MemberMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.spring.mood.projectmvc.service.LoginResult.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SignInServiceTest {


    @Autowired
SignInService signInService;

//    @Test
//    @DisplayName("id가 존재하지 않는 경우를 테스트한다.")
//    void noAccTest () {
//        //given
//        SignInDto dto = SignInDto.builder()
//                .account("pos031678")
//                .password("pos1234")
//                .build();
//        System.out.println("dto = " + dto);
//        //when
//        LoginResult authenticate = signInService.authenticate(dto);
//        System.out.println("authenticate = " + authenticate);
//        //then
//        assertEquals(NO_ACC, authenticate);
//    }
//
//    @Test
//    @DisplayName("id가 존재하는 경우를 테스트한다.")
//    void AccTest () {
//        //given
//        SignInDto dto = SignInDto.builder()
//                .account("pos03167")
//                .password("pos1234")
//                .build();
//        System.out.println("dto = " + dto);
//        //when
//        LoginResult authenticate = signInService.authenticate(dto);
//        System.out.println("authenticate = " + authenticate);
//        //then
//        assertEquals(SUCCESS, authenticate);
//    }
//
//    @Test
//    @DisplayName("PW가 존재하지 않는 경우를 테스트한다.")
//    void noPWTest () {
//        //given
//        SignInDto dto = SignInDto.builder()
//                .account("pos03167")
//                .password("pos12343")
//                .build();
//        System.out.println("dto = " + dto);
//        //when
//        LoginResult authenticate = signInService.authenticate(dto);
//        System.out.println("authenticate = " + authenticate);
//        //then
//        assertEquals(NO_PW, authenticate);
//    }

}