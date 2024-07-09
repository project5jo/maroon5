package com.spring.mood.projectmvc.dto.requestDto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutoSignInDto {

    private String account;
    private String sessionId; //자동로그인 쿠키값
    private LocalDateTime limitTime; //만료시간


}
