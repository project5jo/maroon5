package com.spring.mood.projectmvc.dto.responseDto;

import com.spring.mood.projectmvc.entity.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;


@Setter
@Getter
@ToString
@EqualsAndHashCode
public class ChatMessageDto {
    private Long chatMessageId;
    private String sender;
    private String content;
    private LocalDateTime timestamp;
    private Integer topicId;
    private Integer roomId;
    private String profileUrl;
    private String senderName;

    @PrePersist
    protected void onCreate() {
        this.timestamp = LocalDateTime.now();
    }

}
