package com.spring.mood.projectmvc.repository;

import com.querydsl.core.Tuple;
import com.spring.mood.projectmvc.entity.ChatRoom;

import java.util.List;

public interface ChatRoomRepositoryCustom {
    List<Tuple> findTopicRooms(Integer topicId);
    ChatRoom findChatRoomByTopicIdAndRoomId(Integer topicId, int roomId);
}
