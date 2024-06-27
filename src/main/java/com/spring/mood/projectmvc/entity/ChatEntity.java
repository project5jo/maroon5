package com.spring.mood.projectmvc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString(exclude = "chatRoom")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "chat_messages")
public class ChatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_message_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_account")
    private User user;

    @Column(name = "chat_message", columnDefinition = "TEXT")
    private String content;

    @Column(name = "chat_sent_at")
    private LocalDateTime timestamp;

    @Column(name = "user_profile", length = 255)
    private String userProfile;

    @Column(name = "chat_join")
    private Boolean chatJoin = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    @JsonIgnore
    private ChatRoom chatRoom;

    @Transient
    private int roomId;

    @Transient
    private int topicId;



    @PrePersist
    protected void onCreate() {
        this.timestamp = LocalDateTime.now();
    }
}