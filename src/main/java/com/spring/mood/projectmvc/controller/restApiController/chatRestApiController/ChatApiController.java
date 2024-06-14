package com.spring.mood.projectmvc.controller.restApiController.chatRestApiController;


import com.spring.mood.projectmvc.entity.ChatEntity;
import com.spring.mood.projectmvc.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatApiController {

    private final ChatService chatMessageService;

    @GetMapping("/messages")
    public List<ChatEntity> getMessages() {
        return chatMessageService.getAllMessages();
    }
}
