package com.spring.mood.projectmvc.controller.controller.chatController;


import com.spring.mood.projectmvc.entity.ChatEntity;
import com.spring.mood.projectmvc.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @Autowired
    private ChatService chatMessageService;


    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatEntity sendMessage(ChatEntity message) {
        chatMessageService.saveMessage(message);
        return message;
    }
}
