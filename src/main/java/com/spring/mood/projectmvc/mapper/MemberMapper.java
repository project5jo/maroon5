package com.spring.mood.projectmvc.mapper;

import com.spring.mood.projectmvc.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    boolean save(Member member);

    Member findOne(String userAccount);

    boolean delete(String userAccount);



}