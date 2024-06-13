package com.spring.mood.projectmvc.controller.controller.chatRestApiController;


import com.spring.mood.projectmvc.entity.ChatEntity;
import com.spring.mood.projectmvc.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatApiController {
    @Autowired
    private ChatService chatMessageService;

    @GetMapping("/messages")
    public List<ChatEntity> getMessages() {
        return chatMessageService.getAllMessages();
    }


}
