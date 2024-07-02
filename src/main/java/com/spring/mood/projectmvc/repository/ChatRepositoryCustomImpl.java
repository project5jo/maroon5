package com.spring.mood.projectmvc.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.mood.projectmvc.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class ChatRepositoryCustomImpl implements ChatRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Tuple> findAllCustom(int topicId, int roomId) {
        QUser users = QUser.user;
        QChatEntity chatMessages = QChatEntity.chatEntity;
        QChatRoom chatRooms = QChatRoom.chatRoom;
        QTopic topic = QTopic.topic;

        return jpaQueryFactory.select(
                        users.userAccount,
                        users.userName,
                        chatMessages.content,
                        chatRooms.roomName,
                        topic.topicContent,
                        chatMessages.timestamp,
                        topic.topicId
                )
                .from(users)
                .join(chatMessages).on(users.userAccount.eq(chatMessages.user.userAccount))
                .join(chatRooms).on(chatMessages.chatRoom.roomId.eq(chatRooms.roomId))
                .join(topic).on(chatRooms.topic.topicId.eq(topic.topicId))
                .where(topic.topicId.eq(topicId).and(chatRooms.roomId.eq(roomId)))
                .fetch();
    }

//    //조건을 처리하는 메서드
//    private OrderSpecifier<?> specifier (Integer topicId, int roomId) {
//
//    }
}