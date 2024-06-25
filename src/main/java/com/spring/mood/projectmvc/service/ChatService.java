package com.spring.mood.projectmvc.service;

import com.spring.mood.projectmvc.dto.responseDto.ChatMessageDto;
import com.spring.mood.projectmvc.entity.*;
import com.spring.mood.projectmvc.mapper.MemberMapper;
import com.spring.mood.projectmvc.repository.ChatRepository;
import com.spring.mood.projectmvc.repository.ChatRoomRepository;
import com.spring.mood.projectmvc.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.spring.mood.projectmvc.util.SignInUtil.getLoggedInUserAccount;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository repository;
    private final ChatRoomRepository chatRoomRepository;
    private final TopicRepository topicRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final MemberMapper memberMapper;

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
        ChatRoom chatRoom = findOrCreateChatRoom(topicId, roomId);
//        ChatRoom chatRoom = findChatRoomByTopicAndRoomId(topicId, roomId);
        message.setChatRoom(chatRoom);
//        chatRoom.setCurrentUsers(chatRoom.getCurrentUsers() + 1);
//        chatRoomRepository.save(chatRoom);
        // 새로운 메시지를 클라이언트에 실시간으로 전달
        ChatEntity savedMessage = repository.save(message);
        // 실시간 업데이트를 위한 메시지 브로드캐스트 - 수정된 부분
        messagingTemplate.convertAndSend("/topic/messages/" + topicId + "/" + chatRoom.getRoomId(), convertToDTO(savedMessage));
        return savedMessage;
    }

    @Transactional(readOnly = true)
    public ChatRoom findChatRoomByTopicAndRoomId(int topicId, int roomId) {
        return chatRoomRepository.findByTopicTopicIdAndRoomId(topicId, roomId)
                .orElseThrow(() -> new RuntimeException("ChatRoom not found"));
    }

    @Transactional
    public ChatRoom findOrCreateChatRoom(int topicId, int roomId) {
        Optional<ChatRoom> chatRoomOpt = chatRoomRepository.findByTopicTopicIdAndRoomId(topicId, roomId);

        if (chatRoomOpt.isPresent() && chatRoomOpt.get().getCurrentUsers() < 20) {
            System.out.println("Chat service 에서 chatRoomOpt = " + chatRoomOpt);
            return chatRoomOpt.get();
        } else {
            // Topic을 조회
            Optional<Topic> topicOpt = topicRepository.findById(topicId);
            if (topicOpt.isEmpty()) {
                throw new RuntimeException("Topic not found");
            }
            Topic topic = topicOpt.get();

            ChatRoom newChatRoom = ChatRoom.builder()
                    .topic(topic)
                    .roomName("Room " + (roomId + 1))
                    .currentUsers(0)
                    .build();
            return chatRoomRepository.save(newChatRoom);
        }
    }

    @Transactional
    public void incrementCurrentUsers(int topicId, int roomId) {
        ChatRoom chatRoom = findChatRoomByTopicAndRoomId(topicId, roomId);
        chatRoom.setCurrentUsers(chatRoom.getCurrentUsers() + 1);
        chatRoomRepository.save(chatRoom);
    }

    @Transactional
    public void decrementCurrentUsers(HttpSession session) {
        String loggedInUserAccount = getLoggedInUserAccount(session);
        User chatUser = memberMapper.findChatUser(loggedInUserAccount);
        ChatRoom chatRoom = findChatRoomByTopicAndRoomId(chatUser.getChatRoom().getRoomId(), chatUser.getChatRoom().getTopic().getTopicId());
        System.out.println("서비스에서 챗룸~ = " + chatRoom);
        chatRoom.setCurrentUsers(chatRoom.getCurrentUsers() - 1);
        chatRoomRepository.save(chatRoom);
    }

//    @Transactional(readOnly = true)
//    public ChatRoom findChatRoomByTopicAndRoomId2(int topicId, int roomId) {
//        return chatRoomRepository.findByTopicIdAndRoomId(topicId, roomId)
//                .orElseThrow(() -> new RuntimeException("ChatRoom not found"));
//    }
}
