package com.spring.mood.projectmvc.mapper;

import com.spring.mood.projectmvc.dto.responseDto.ResponseChatDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface ChatMapper {

    List<ResponseChatDto> findAll(@Param("topic")long topic, @Param("room") long room);
}