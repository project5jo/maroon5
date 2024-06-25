package com.spring.mood.projectmvc.service;

import com.spring.mood.projectmvc.dto.responseDto.ChatMessageDto;
import com.spring.mood.projectmvc.entity.ChatEntity;
import com.spring.mood.projectmvc.entity.ChatRoom;
import com.spring.mood.projectmvc.repository.ChatRepository;
import com.spring.mood.projectmvc.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository repository;
    private final ChatRoomRepository chatRoomRepository;

    public List<ChatMessageDto> getAllMessages(int topicId, int roomId) {
        return repository.findMessagesByChatRoomTopicTopicIdAndChatRoomRoomId(topicId, roomId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ChatMessageDto convertToDTO(ChatEntity chatEntity) {
        ChatMessageDto dto = new ChatMessageDto();
        dto.setChatMessageId(chatEntity.getId());
        dto.setContent(chatEntity.getContent());
        dto.setTimestamp(chatEntity.getTimestamp());
//        System.out.println("chatservice 에서 dto 가져오기 = " + dto);
        dto.setSender(chatEntity.getUser().getUserAccount());
        return dto;
    }

    @Transactional
    public ChatEntity saveMessage(ChatEntity message, int topicId, int roomId) {
        ChatRoom chatRoom = findChatRoomByTopicAndRoomId(topicId, roomId);
        message.setChatRoom(chatRoom);
        return repository.save(message);
    }

    @Transactional(readOnly = true)
    public ChatRoom findChatRoomByTopicAndRoomId(int topicId, int roomId) {
        return chatRoomRepository.findByTopicTopicIdAndRoomId(topicId, roomId)
                .orElseThrow(() -> new RuntimeException("ChatRoom not found"));
    }
}