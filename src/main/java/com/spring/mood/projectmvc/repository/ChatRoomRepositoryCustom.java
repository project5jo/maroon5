package com.spring.mood.projectmvc.repository;

import com.querydsl.core.Tuple;
import com.spring.mood.projectmvc.entity.ChatRoom;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepositoryCustom {
    List<Tuple> findTopicRooms(Integer topicId);
    Optional<ChatRoom> findChatRoom(Integer topicId, int roomId);
}
