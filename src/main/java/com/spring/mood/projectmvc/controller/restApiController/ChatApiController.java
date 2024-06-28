package com.spring.mood.projectmvc.controller.restApiController;


import com.spring.mood.projectmvc.dto.responseDto.ChatMessageDto;
import com.spring.mood.projectmvc.entity.ChatEntity;
import com.spring.mood.projectmvc.entity.ChatRoom;
import com.spring.mood.projectmvc.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatApiController {

    private static final Logger log = LoggerFactory.getLogger(ChatApiController.class);
    private final ChatService chatService;


    @GetMapping("/messages")
    public List<ChatMessageDto> getMessages(@RequestParam(name = "topicId") Integer topicId,
                                            @RequestParam(name = "roomId") int roomId) {
        return chatService.getAllMessages(topicId, roomId);
    }

    @PostMapping("/message")
    public ChatEntity sendMessage(@RequestBody ChatEntity message,
                                  @RequestParam(name = "topicId") Integer topicId) {
        return chatService.saveMessage(message, topicId);
    }
    @GetMapping("/findOrCreateRoom")
    public ChatRoom findOrCreateRoom(@RequestParam(name = "topicId") Integer topicId,
                                     @RequestParam(name = "roomId") int roomId) {
        return chatService.findOrCreateChatRoom(topicId, roomId);
    }

    @PostMapping("/joinRoom")
    public ChatRoom joinRoom(@RequestBody Map<String, Object> payload) {
        Integer topicId = (Integer) payload.get("topicId");
        int roomId = (int) payload.get("roomId");
        String username = (String) payload.get("username");
        return chatService.incrementCurrentUsers(topicId, roomId, username);
    }
}
