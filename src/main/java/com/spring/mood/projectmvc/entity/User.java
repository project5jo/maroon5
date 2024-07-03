package com.spring.mood.projectmvc.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString(exclude ={"chatRoom", "chatMessages"})
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_account", length = 50)
    private String userAccount;

    @Column(name = "user_role", length = 50)
    private String userRole;

    @Column(name = "user_name", length = 50)
    private String userName;

    @Column(name = "user_password", length = 255)
    private String userPassword;

    @Column(name = "user_email", length = 100)
    private String userEmail;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "user_birth")
    private LocalDate userBirthDate;

    @Column(name = "user_address", length = 255)
    private String userAddress;

    @Column(name = "user_profile", length = 255)
    private String userProfile;

    @Column(name = "user_point")
    private Integer userPoint;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private ChatRoom chatRoom;

    @OneToMany(mappedBy = "user")
    private List<ChatEntity> chatMessages;
}