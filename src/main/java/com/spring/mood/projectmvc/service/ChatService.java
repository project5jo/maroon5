package com.spring.mood.projectmvc.service;

import com.querydsl.core.Tuple;
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
        return repository.findAllCustom(topicId, roomId).stream()
                .map(this::convertTupleToDTO)
                .collect(Collectors.toList());
    }

    private ChatMessageDto convertTupleToDTO(Tuple tuple) {
        ChatMessageDto dto = new ChatMessageDto();
        dto.setChatMessageId(tuple.get(QChatEntity.chatEntity.id));
        dto.setContent(tuple.get(QChatEntity.chatEntity.content));
        dto.setTimestamp(tuple.get(QChatEntity.chatEntity.timestamp));
        dto.setSender(tuple.get(QUser.user.userAccount));
        dto.setSenderName(tuple.get(QUser.user.userName));
        dto.setTopicId(tuple.get(QTopic.topic.topicId));
        dto.setRoomId(tuple.get(QChatRoom.chatRoom.roomId));
        dto.setProfileUrl(tuple.get(QUser.user.userProfile)); // 프로필 URL 설정
        return dto;
    }

    @Transactional
    public ChatEntity saveMessage(ChatEntity message, Integer topicId) {
        ChatRoom chatRoom = findChatRoomByTopicAndRoomId(topicId, message.getRoomId()).orElseThrow(()
                ->new RuntimeException("chatroom not found임 ㅋㅋ"));
//                .orElseThrow(() -> new RuntimeException("ChatRoom not found")); // (수정)
        message.setChatRoom(chatRoom);
        return repository.save(message);
    }
    public ChatMessageDto convertToDTO(ChatEntity chatEntity) {
        ChatMessageDto dto = new ChatMessageDto();
        dto.setChatMessageId(chatEntity.getId());
        dto.setContent(chatEntity.getContent());
        dto.setTimestamp(chatEntity.getTimestamp());
        dto.setSender(chatEntity.getUser().getUserAccount());
        dto.setSenderName(chatEntity.getUser().getUserName());
        dto.setTopicId(chatEntity.getChatRoom().getTopic().getTopicId());
        dto.setRoomId(chatEntity.getChatRoom().getRoomId());
        dto.setProfileUrl(chatEntity.getUser().getUserProfile());
        return dto;
    }

    @Transactional(readOnly = true)
    public Optional<ChatRoom> findChatRoomByTopicAndRoomId(Integer topicId, int roomId) {
        return chatRoomRepository.findChatRoom(topicId, roomId); // (수정)
    }

    @Transactional
    public ChatRoom findOrCreateChatRoom(Integer topicId, int roomId) {
        Optional<ChatRoom> chatRoomOpt = findChatRoomByTopicAndRoomId(topicId, roomId); // (수정)
        if (chatRoomOpt.isPresent()) { // (수정)
            ChatRoom chatRoom = chatRoomOpt.get(); //
            if (chatRoom.getCurrentUsers() < 50) {
                return chatRoom;
            } else {
                return createNewChatRoom(topicId, roomId + 1);
            }
        } else {
            return createNewChatRoom(topicId, roomId);
        }
    }

    private ChatRoom createNewChatRoom(Integer topicId, int newRoomId) { // (수정)
        System.out.println("새 채팅방 생성 - topicId: " + topicId + ", newRoomId: " + newRoomId);
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new RuntimeException("Topic not found"));
        ChatRoom newChatRoom = ChatRoom.builder()
                .topic(topic)
                .roomName("Room " + newRoomId)
                .currentUsers(0)
                .build();
        return chatRoomRepository.save(newChatRoom);
    }
    @Transactional
    public ChatRoom incrementCurrentUsers(Integer topicId, int roomId, String username) { // (수정)
        System.out.println("username 은은은은= " + username);

        Optional<ChatRoom> chatRoomOpt = findChatRoomByTopicAndRoomId(topicId, roomId); // (수정)
        ChatRoom chatRoom = chatRoomOpt.orElse(null); // (수정)

        if (chatRoom == null || chatRoom.getCurrentUsers() >= 50) {
            chatRoom = findOrCreateAvailableChatRoom(topicId); // (수정)
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
    public ChatRoom findOrCreateAvailableChatRoom(Integer topicId) { // (수정)
        List<ChatRoom> chatRooms = chatRoomRepository.findByTopicTopicIdOrderByRoomIdAsc(topicId); // (수정)

        for (ChatRoom chatRoom : chatRooms) { // (수정)
            if (chatRoom.getCurrentUsers() < 50) { // (수정)
                return chatRoom; // (수정)
            }
        }

        int newRoomId = chatRooms.isEmpty() ? 1 : chatRooms.get(chatRooms.size() - 1).getRoomId() + 1; // (수정)
        return createNewChatRoom(topicId, newRoomId); // (수정)
    }

    @Transactional
    public ChatRoom findOrCreateAvailableChatRoomForTopic(Integer topicId) { // (수정)
        List<ChatRoom> chatRooms = chatRoomRepository.findByTopicTopicIdOrderByRoomIdAsc(topicId); // (수정)

        for (ChatRoom chatRoom : chatRooms) { // (수정)
            if (chatRoom.getCurrentUsers() < 50) { // (수정)
                System.out.println("존재");
                return chatRoom; // (수정)
            }
        }

        System.out.println("없음");
        int newRoomId = chatRooms.isEmpty() ? 1 : chatRooms.get(chatRooms.size() - 1).getRoomId() + 1; // (수정)
        return createNewChatRoom(topicId, newRoomId); // (수정)
    }


    public ChatRoom allocateRoomForUser(Integer topicId) {
        return findOrCreateAvailableChatRoomForTopic(topicId);
    }


}