package com.spring.mood.projectmvc.repository;

import com.spring.mood.projectmvc.entity.ChatEntity;

import java.util.List;

public interface ChatRepositoryCustom {
    List<ChatEntity> findAllCustom();
}