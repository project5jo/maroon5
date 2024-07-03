package com.spring.mood.projectmvc.repository;

import com.spring.mood.projectmvc.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>,  ChatRoomRepositoryCustom {
    Optional<ChatRoom> findByTopicTopicIdAndRoomId(Integer topicId, int roomId);

    List<ChatRoom> findByTopicTopicIdOrderByRoomIdAsc(Integer topicId);
}
