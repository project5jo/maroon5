package com.spring.mood.projectmvc.controller.restApiController;


import com.spring.mood.projectmvc.dto.responseDto.ChatMessageDto;
import com.spring.mood.projectmvc.entity.ChatEntity;
import com.spring.mood.projectmvc.entity.ChatRoom;
import com.spring.mood.projectmvc.entity.Topic;
import com.spring.mood.projectmvc.repository.TopicRepository;
import com.spring.mood.projectmvc.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatApiController {

    private static final Logger log = LoggerFactory.getLogger(ChatApiController.class);
    private final ChatService chatService;
    private final TopicRepository topicRepository;


    @GetMapping("/messages")
    public List<ChatMessageDto> getMessages(@RequestParam(name = "topicId") Integer topicId,
                                            @RequestParam(name = "roomId") int roomId) {

        log.debug("Received request to get messages for topicId: {}, roomId: {}", topicId, roomId);
        return chatService.getAllMessages(topicId, roomId);
    }


    @PostMapping("/message")
    public ChatEntity sendMessage(@RequestBody ChatEntity message,
                                  @RequestParam(name = "topicId") Integer topicId) {
        log.debug("Received request to post messages for topicId: {}, roomId: {}", topicId);
        return chatService.saveMessage(message, topicId);
    }
    @GetMapping("/findOrCreateRoom")
    public ChatRoom findOrCreateRoom(@RequestParam(name = "topicId") Integer topicId,
                                     @RequestParam(name = "roomId") int roomId) {
        return chatService.findOrCreateChatRoom(topicId, roomId);
    }

    @PostMapping("/joinRoom")
    public ResponseEntity<Map<String, Object>> joinRoom(@RequestBody Map<String, Object> payload, HttpSession session) {
        Integer topicId = (Integer) payload.get("topicId");
        String username = (String) payload.get("username");

        ChatRoom chatRoom = chatService.allocateRoomForUser(topicId);
        Topic topic = topicRepository.findById(topicId).orElseThrow();

        session.removeAttribute("roomId");
        session.setAttribute("topicId", chatRoom.getTopic().getTopicId());
        session.setAttribute("roomId", chatRoom.getRoomId());


        chatService.incrementCurrentUsers(topicId, chatRoom.getRoomId(), username);

        Map<String, Object> response = new HashMap<>();
        response.put("roomId", chatRoom.getRoomId());
        response.put("topicContent", topic.getTopicContent());

        return ResponseEntity.ok(response);
    }
}
