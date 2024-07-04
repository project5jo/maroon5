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


    /**
     * 주제번호, 방번호로 find 해서 저장된 메세지들을 가져옴.
     * @param topicId - 주제 번호
     * @param roomId - 방 번호
     * @return dto 형태의 채팅방의 채팅을 전부 로드시킴.
     */
    @GetMapping("/messages")
    public List<ChatMessageDto> getMessages(@RequestParam(name = "topicId") Integer topicId,
                                            @RequestParam(name = "roomId") int roomId) {
        return chatService.getAllMessages(topicId, roomId);
    }


    /**
     * 채팅 입력한 걸 디비에 저장시켜야 함.
     * @param message - 입력한 메세지
     * @param topicId - 주제 번호
     * @return
     */
    @PostMapping("/message")
    public ChatEntity sendMessage(@RequestBody ChatEntity message,
                                  @RequestParam(name = "topicId") Integer topicId) {
        return chatService.saveMessage(message, topicId);
    }


    /**
     * 토픽, 방 번호로 새로운 방을 생성
     * @param topicId - 주제 번호
     * @param roomId - 방 번호
     * @return
     */
    @GetMapping("/findOrCreateRoom")
    public ChatRoom findOrCreateRoom(@RequestParam(name = "topicId") Integer topicId,
                                     @RequestParam(name = "roomId") int roomId) {
        return chatService.findOrCreateChatRoom(topicId, roomId);
    }


    /**
     * 해당하는 방이 50명 이하인지 확인하고 없다면 새로운 방을 생성해서 입장시킴.
     * 해당하는 topic의 주제도 response 객체에 담아서 전송.
     * @param payload - topicId와 로그인한 회원의 username이 들어가 있음.
     * @param session - 로그인 한 사람의 session 정보가 있음.
     * @return
     */
    @PostMapping("/joinRoom")
    public ResponseEntity<Map<String, Object>> joinRoom(@RequestBody Map<String, Object> payload, HttpSession session) {
        Integer topicId = (Integer) payload.get("topicId");
        String username = (String) payload.get("username");

        ChatRoom chatRoom = chatService.roomJoin(topicId);
        Topic topic = topicRepository.findById(topicId).orElseThrow();

        session.removeAttribute("roomId");
        session.setAttribute("topicId", chatRoom.getTopic().getTopicId());
        session.setAttribute("roomId", chatRoom.getRoomId());


        chatService.increaseCurrentUser(topicId, chatRoom.getRoomId(), username);

        Map<String, Object> response = new HashMap<>();
        response.put("roomId", chatRoom.getRoomId());
        response.put("topicContent", topic.getTopicContent());

        return ResponseEntity.ok(response);
    }
}
