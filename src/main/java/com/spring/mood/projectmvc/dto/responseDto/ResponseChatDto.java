package com.spring.mood.projectmvc.dto.responseDto;


import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class ResponseChatDto {

    private String userAccount;
    private String userName;
    private String chatMessage;
    private String roomName;
    private String topicContent;
    private String chatSentAt;
}
