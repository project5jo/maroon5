package com.spring.mood.projectmvc.mapper;


import com.spring.mood.projectmvc.entity.Member;
import com.spring.mood.projectmvc.entity.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TopicMapper {
    boolean save(String topicContent);

    List<Topic> findAll();
}
