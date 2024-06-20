package com.spring.mood.projectmvc.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Member {

    private String userAccount; // PK

    private int roomId; // ChatRooms FK

    private String userRole;
    private String userName;
    @Setter
    private String userPassword;
    private String userEmail;
    private LocalDateTime createAt;
    private LocalDate userBirth;
    private String userAddress;
    private String userProfile;
    private int userPoint;
    private String sessionId; //자동로그인 쿠키값
    private LocalDateTime limitTime; //만료시간

}
