package com.spring.mood.projectmvc.controller.controller;


import com.spring.mood.projectmvc.dto.responseDto.ChatMessageDto;
import com.spring.mood.projectmvc.dto.responseDto.SignInUserInfoDTO;
import com.spring.mood.projectmvc.entity.ChatEntity;
import com.spring.mood.projectmvc.entity.User;
import com.spring.mood.projectmvc.mapper.MemberMapper;
import com.spring.mood.projectmvc.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;


@Controller
@RequiredArgsConstructor
public class ChatController {



    private final ChatService chatMessageService;
    private final MemberMapper memberMapper;

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessageDto sendMessage(ChatEntity message, SimpMessageHeaderAccessor headerAccessor) {
        System.out.println("컨트롤러에서 메세지 = " + message);
        Object loginUser = headerAccessor.getSessionAttributes().get("loginUser");
        System.out.println("컨트롤러에서 로그인유저 메세지 = " + loginUser);

        if (loginUser == null) {
            throw new IllegalStateException("로그인 사용자를 찾을 수 없습니다.");
        }

        // SignInUserInfoDTO로 캐스팅
        SignInUserInfoDTO loginUserDto = (SignInUserInfoDTO) loginUser;

        // SignInUserInfoDTO에서 User 객체로 변환
        User chatUser = memberMapper.findChatUser(((SignInUserInfoDTO) loginUser).getAccount());

        System.out.println("컨트롤러에서 유저 메세지 = " + chatUser);
//        System.out.println("컨트롤러에서 유저 chatroom" + message.getChatRoom().getTopic().getTopicId());
//        System.out.println("컨트롤러에서 유저 chatroom id" + message.getChatRoom().getRoomId());
        message.setUser(chatUser);
        message.setTimestamp(LocalDateTime.now());

        // ChatRoom 설정
//        int topicId = message.getChatRoom().getTopic().getTopicId();
//        int roomId = message.getChatRoom().getRoomId();
        int topicId = message.getTopicId();
        int roomId = message.getRoomId();
//        int topicId = 1;  // 실제 값으로 교체
//        int roomId = 1;
        System.out.println("message = " + message);
        chatMessageService.saveMessage(message, topicId, roomId);

        return chatMessageService.convertToDTO(message);

    }
}
