package com.spring.mood.projectmvc.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "Users")
public class Member {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_account")
    @Setter
    private String userAccount; // PK

    @Setter
    @Column(name = "room_id")
    private int roomId; // ChatRooms FK

    @Column(name = "user_role")
    private String userRole;
    @Setter
    @Column(name = "user_name")
    private String userName;

    @Setter
    @Column(name = "user_password")
    private String userPassword;
    @Setter
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Setter
    @Column(name = "user_birth")
    private LocalDate userBirth;
    @Column(name = "user_address")
    private String userAddress;

    @Setter
    @Column(name = "user_profile")
    private String userProfile;
    @Column(name = "user_point")

    @Setter
    private int userPoint;
    @Column(name = "session_id")
    private String sessionId; //자동로그인 쿠키값
    @Column(name = "limit_time")
    private LocalDateTime limitTime; //만료시간

}
