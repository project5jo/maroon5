package com.spring.mood.projectmvc.entity;


import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ChatEntity {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sender;
    private String content;
    private LocalDateTime timestamp;

    public ChatEntity(String sender, String content, LocalDateTime timestamp) {
        this.sender = sender;
        this.content = content;
        this.timestamp = LocalDateTime.parse(timestamp.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")));
    }
}
