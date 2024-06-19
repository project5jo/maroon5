package com.spring.mood.projectmvc.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "chatmessages")
public class ChatEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id


    @Column(name = "user_account")
    private String sender;

    @Column(name = "chat_message")
    private String content;

    @Column(name = "chat_sent_at")
    private LocalDateTime timestamp;

    @Column(name = "room_id")
    private int roomId;

    @PrePersist
    protected void onCreate() {
        this.timestamp = LocalDateTime.now();
            this.roomId = 1; // 기본 값 설정, 필요에 따라 수정
    }
}