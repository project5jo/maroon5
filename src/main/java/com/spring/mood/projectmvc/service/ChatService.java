package com.spring.mood.projectmvc.service;

import com.spring.mood.projectmvc.dto.responseDto.ChatMessageDto;
import com.spring.mood.projectmvc.entity.*;
import com.spring.mood.projectmvc.mapper.MemberMapper;
import com.spring.mood.projectmvc.repository.ChatRepository;
import com.spring.mood.projectmvc.repository.ChatRoomRepository;
import com.spring.mood.projectmvc.repository.TopicRepository;
import com.spring.mood.projectmvc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private final ChatRepository repository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final MemberMapper memberMapper;

    public List<ChatMessageDto> getAllMessages(Integer topicId, int roomId) {
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
        dto.setSenderName(chatEntity.getUser().getUserName());
        dto.setTopicId((long) chatEntity.getTopicId());
        dto.setRoomId((long) chatEntity.getRoomId());
        dto.setProfileUrl(chatEntity.getUser().getUserProfile()); // 프로필 URL 설정
        return dto;
    }

    @Transactional
    public ChatEntity saveMessage(ChatEntity message, Integer topicId) {
        System.out.println("message = " + message);
        ChatRoom chatRoom = findChatRoomByTopicAndRoomId(topicId, message.getRoomId());
        message.setChatRoom(chatRoom);
        return repository.save(message);
    }

    @Transactional(readOnly = true)
    public ChatRoom findChatRoomByTopicAndRoomId(Integer topicId, int roomId) {
        return chatRoomRepository.findByTopicTopicIdAndRoomId( topicId, roomId)
                .orElseThrow(() -> new RuntimeException("ChatRoom not found"));
    }

    @Transactional
    public ChatRoom findOrCreateChatRoom(Integer topicId, int roomId) {
        Optional<ChatRoom> chatRoomOpt = chatRoomRepository.findByTopicTopicIdAndRoomId(topicId,  roomId);
        if (chatRoomOpt.isPresent()) {
            ChatRoom chatRoom = chatRoomOpt.get();
            if (chatRoom.getCurrentUsers() < 50) {
                return chatRoom;
            } else {
                return createNewChatRoom(topicId, roomId + 1);
            }
        } else {
            return createNewChatRoom(topicId, roomId);
        }
    }

    private ChatRoom createNewChatRoom(Integer topicId, int newRoomId) {
        Optional<Topic> topicOpt = topicRepository.findById(topicId);
        if (topicOpt.isEmpty()) {
            throw new RuntimeException("Topic not found");
        }
        Topic topic = topicOpt.get();

        ChatRoom newChatRoom = ChatRoom.builder()
                .topic(topic)
                .roomName("Room " + newRoomId)
                .roomId(newRoomId)
                .currentUsers(0)
                .build();
        return chatRoomRepository.save(newChatRoom);
    }
    @Transactional
    public ChatRoom incrementCurrentUsers(Integer topicId, int roomId, String username) {
        System.out.println("username 은은은은= " + username);
        ChatRoom chatRoom = findChatRoomByTopicAndRoomId(topicId, roomId);
        if (chatRoom.getCurrentUsers() >= 50) {
            chatRoom = createNewChatRoom(topicId, roomId + 1);
        }

        chatRoom.setCurrentUsers(chatRoom.getCurrentUsers() + 1);
        chatRoomRepository.save(chatRoom);

        Member one = memberMapper.findOne(username);
        String userAccount = one.getUserAccount();
        int roomId1 = chatRoom.getRoomId();
        System.out.println("roomId1 = " + roomId1);
        memberMapper.updateRoomId(userAccount, roomId1);
        System.out.println("one = " + one);

        return chatRoom;
    }

    @Transactional
    public ChatRoom findOrCreateAvailableChatRoom(Integer topicId) {
        List<ChatRoom> chatRooms = chatRoomRepository.findByTopicTopicIdOrderByRoomIdAsc(topicId);

        for (ChatRoom chatRoom : chatRooms) {
            if (chatRoom.getCurrentUsers() < 50) {
                return chatRoom;
            }
        }

        // 모든 방이 꽉 찼다면 새로운 방을 생성
        int newRoomId = chatRooms.isEmpty() ? 1 : chatRooms.get(chatRooms.size() - 1).getRoomId() + 1;
        return createNewChatRoom(topicId, newRoomId);
    }

}