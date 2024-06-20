package com.spring.mood.projectmvc.repository;

import com.spring.mood.projectmvc.entity.ChatEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class ChatRepositoryCustomImpl implements ChatRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ChatEntity> findAllCustom() {
        // JPQL 문법으로 쿼리 작성
        TypedQuery<ChatEntity> query = entityManager.createQuery("SELECT c FROM ChatEntity c", ChatEntity.class);
        return query.getResultList();
    }
}