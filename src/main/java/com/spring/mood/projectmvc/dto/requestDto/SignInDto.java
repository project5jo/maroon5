package com.spring.mood.projectmvc.dto.requestDto;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class SignInDto {
    private String account; // 유저 계정
    private String password; // 유저 비밀번호
    private boolean autoLogin; // 자동로그인 체크 여부
}
