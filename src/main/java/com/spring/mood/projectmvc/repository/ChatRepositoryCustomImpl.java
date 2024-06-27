package com.spring.mood.projectmvc.repository;

import com.spring.mood.projectmvc.entity.ChatEntity;
import com.spring.mood.projectmvc.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class ChatRepositoryCustomImpl implements ChatRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ChatEntity> findAllCustom(int topicId, int roomId) {
        String jpql = "SELECT cm FROM ChatEntity cm " +
                "JOIN cm.chatRoom cr " +
                "JOIN cr.topic t " +
                "WHERE t.topicId = :topicId " +
                "AND cr.roomId = :roomId";

        TypedQuery<ChatEntity> query = entityManager.createQuery(jpql, ChatEntity.class);
        query.setParameter("topicId", topicId);
        query.setParameter("roomId", roomId);
        return query.getResultList();
    }
}