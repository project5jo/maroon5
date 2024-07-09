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


    /**
     * tuple 형태로 받은 메세지를 map에 담아서 convert 시킴.
     * @param topicId - 주제 번호
     * @param roomId - 방 번호
     * @return 채팅방에 저장된 메세지들을 리턴
     */
    public List<ChatMessageDto> getAllMessages(Integer topicId, int roomId) {
        return repository.findAllCustom(topicId, roomId).stream()
                .map(this::convertTupleToDTO)
                .collect(Collectors.toList());
    }


    /**
     * getAllMessages에서 tuple로 받은 메세지들을 dto 로 변환
     * @param tuple - Messages로 받은 tuple
     * @return - dto
     */
    private ChatMessageDto convertTupleToDTO(Tuple tuple) {
        ChatMessageDto dto = new ChatMessageDto();
        dto.setChatMessageId(tuple.get(QChatEntity.chatEntity.id));
        dto.setContent(tuple.get(QChatEntity.chatEntity.content));
        dto.setTimestamp(tuple.get(QChatEntity.chatEntity.timestamp));
        dto.setSender(tuple.get(QUser.user.userAccount));
        dto.setSenderName(tuple.get(QUser.user.userName));
        dto.setTopicId(tuple.get(QTopic.topic.topicId));
        dto.setRoomId(tuple.get(QChatRoom.chatRoom.roomId));
        dto.setProfileUrl(tuple.get(QUser.user.userProfile));
        return dto;
    }


    /**
     *
     * @param message - 사용자가 입력한 채팅 메세지
     * @param topicId - 주제 번호
     * @return 채팅에 메시지 저장
     */
    @Transactional
    public ChatEntity saveMessage(ChatEntity message, Integer topicId) {
        ChatRoom chatRoom = findChatRoomByTopicAndRoomId(topicId, message.getRoomId()).orElseThrow(()
                ->new RuntimeException("chatroom not found임 ㅋㅋ"));
        message.setChatRoom(chatRoom);
        return repository.save(message);
    }


    /**
     *
     * @param chatEntity - 채팅 메세지
     * @return - dto로 변환해줌.
     */
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


    /**
     *
     * @param topicId - 주제 번호
     * @param roomId - 방 번호
     * @return - 주제 ,방 번호로 채팅방 검색
     */
    @Transactional(readOnly = true)
    public Optional<ChatRoom> findChatRoomByTopicAndRoomId(Integer topicId, int roomId) {
        return chatRoomRepository.findChatRoom(topicId, roomId);
    }


    /**
     * 참여하고 있는 유저 수가 50명이 넘는다면 새로운 채팅방을 만들어서 리턴.
     * @param topicId
     * @param roomId
     * @return - 방 없으면 createNewChatRoom 으로 리턴시킴.
     */
    @Transactional
    public ChatRoom findOrCreateChatRoom(Integer topicId, int roomId) {
        Optional<ChatRoom> chatRoomOpt = findChatRoomByTopicAndRoomId(topicId, roomId);
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


    /**
     * 방 번호랑 새로운 채팅방을 만들 번호를 받아서 새로운 채팅방 생성
     * @param topicId
     * @param newRoomId - 새로 만들 채팅방의 번호임.
     * @return 새로운 채팅방 DB에 추가함.
     */
    private ChatRoom createNewChatRoom(Integer topicId, int newRoomId) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new RuntimeException("Topic not found"));
        ChatRoom newChatRoom = ChatRoom.builder()
                .topic(topic)
                .roomName("Room " + newRoomId)
                .currentUsers(0)
                .build();
        return chatRoomRepository.save(newChatRoom);
    }


    /**
     * 로그인 한 사람의 채팅방 번호도 디비에서 바꿔줌.
     * @param topicId - 주제 번호
     * @param roomId - 방 번호
     * @param username - 로그인한 사람의 유저 이름
     * @return - 들어가있는 채팅방
     */
    @Transactional
    public ChatRoom increaseCurrentUser(Integer topicId, int roomId, String username) {

        Optional<ChatRoom> chatRoomOpt = findChatRoomByTopicAndRoomId(topicId, roomId);
        ChatRoom chatRoom = chatRoomOpt.orElse(null);

        if (chatRoom == null || chatRoom.getCurrentUsers() >= 50) {
            chatRoom = findRoomsByTopic(topicId);
        }

        chatRoom.setCurrentUsers(chatRoom.getCurrentUsers() + 1);
        chatRoomRepository.save(chatRoom);

        Member one = memberMapper.findOne(username);
        String userAccount = one.getUserAccount();
        int roomId1 = chatRoom.getRoomId();
        memberMapper.updateRoomId(userAccount, roomId1);
        return chatRoom;
    }

    /**
     * 주제 번호로 채팅방을 찾아줌.
     * @param topicId - 주제 번호
     * @return
     */
    @Transactional
    public ChatRoom findRoomsByTopic(Integer topicId) {
        List<ChatRoom> chatRooms = chatRoomRepository.findByTopicTopicIdOrderByRoomIdAsc(topicId);

        for (ChatRoom chatRoom : chatRooms) {
            if (chatRoom.getCurrentUsers() < 50) {
                return chatRoom;
            }
        }

        int newRoomId = chatRooms.isEmpty() ? 1 : chatRooms.get(chatRooms.size() - 1).getRoomId() + 1;
        return createNewChatRoom(topicId, newRoomId);
    }


    public ChatRoom roomJoin(Integer topicId) {
        return findRoomsByTopic(topicId);
    }


}