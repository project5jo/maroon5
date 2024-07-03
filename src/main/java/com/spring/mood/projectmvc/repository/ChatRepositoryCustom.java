package com.spring.mood.projectmvc.repository;

import com.querydsl.core.Tuple;

import java.util.List;

public interface ChatRepositoryCustom {
    List<Tuple> findAllCustom(int topicId, int roomId);
}