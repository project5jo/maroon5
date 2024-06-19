package com.spring.mood.projectmvc.controller.controller;

import com.spring.mood.projectmvc.dto.requestDto.SignInDto;
import com.spring.mood.projectmvc.service.LoginResult;
import com.spring.mood.projectmvc.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SignInControllerTest {

    @Autowired
    SignInService service;

    @Test
    @DisplayName("id가 존재하지 않는 경우를 테스트한다.")
    void noAccTest() {
        //given
        SignInDto dto = SignInDto.builder()
                .account("pos031676")
                .password("pos1234")
                .build();
        //when
         LoginResult result = service.authenticate(dto);
        //then
        assertEquals(LoginResult.NO_ACC, result);
    }


}