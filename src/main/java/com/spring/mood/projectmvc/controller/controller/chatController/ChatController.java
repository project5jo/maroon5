package com.spring.mood.projectmvc.controller.controller.chatController;


import com.spring.mood.projectmvc.entity.ChatEntity;
import com.spring.mood.projectmvc.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Date;


@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatMessageService;


    /** 3번
     * 브라우저에서 app/sendMessage 라는 요청을 보내면 message 매개변수로 확인 한 다음 메세지 세이브 시키고 리턴시킴.~
     * sendTo는 클라이언트에서 브라우저로 /topic/messages 로 렌더링을 위한 메세지를 보내줌.
     */
    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatEntity sendMessage(ChatEntity message) {
        message.setTimestamp(LocalDateTime.now());
        chatMessageService.saveMessage(message);
        return message;
    }
}
