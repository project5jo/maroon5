package com.spring.mood.projectmvc.repository;

import com.spring.mood.projectmvc.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
}
