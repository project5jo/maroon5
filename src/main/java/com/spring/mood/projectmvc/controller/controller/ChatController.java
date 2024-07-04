package com.spring.mood.projectmvc.controller.controller;


import com.spring.mood.projectmvc.dto.responseDto.ChatMessageDto;
import com.spring.mood.projectmvc.dto.responseDto.SignInUserInfoDTO;
import com.spring.mood.projectmvc.entity.ChatEntity;
import com.spring.mood.projectmvc.entity.ChatRoom;
import com.spring.mood.projectmvc.entity.User;
import com.spring.mood.projectmvc.mapper.MemberMapper;
import com.spring.mood.projectmvc.repository.ChatRoomRepository;
import com.spring.mood.projectmvc.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
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
    private final ChatRoomRepository chatRoomRepository;

@MessageMapping("/sendMessage/{topicId}/{roomId}")
@SendTo("/topic/messages/{topicId}/{roomId}")
public ChatMessageDto sendMessage(@DestinationVariable Integer topicId, @DestinationVariable int roomId, ChatEntity message, SimpMessageHeaderAccessor headerAccessor) {
    System.out.println("controller topicId = " + topicId);
    Object loginUser = headerAccessor.getSessionAttributes().get("loginUser");

    if (loginUser == null) {
        throw new IllegalStateException("로그인 사용자를 찾을 수 없습니다.");
    }
    // SignInUserInfoDTO로 캐스팅
    SignInUserInfoDTO loginUserDto = (SignInUserInfoDTO) loginUser;

    // SignInUserInfoDTO에서 User 객체로 변환
    User chatUser = memberMapper.findChatUser(loginUserDto.getAccount());

    System.out.println("컨트롤러에서 유저 메세지 = " + chatUser);
    message.setUser(chatUser);
    message.setTimestamp(LocalDateTime.now());

    System.out.println("message = " + message);
    chatMessageService.saveMessage(message, topicId);

    return chatMessageService.convertToDTO(message);
}
}
