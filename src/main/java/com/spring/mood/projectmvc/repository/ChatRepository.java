package com.spring.mood.projectmvc.repository;

import com.spring.mood.projectmvc.entity.ChatEntity;
import com.spring.mood.projectmvc.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, Long> {
    List<ChatEntity> findMessagesByChatRoomTopicTopicIdAndChatRoomRoomId(int topicId, int roomId);
}


