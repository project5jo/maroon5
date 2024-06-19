package com.spring.mood.projectmvc.mapper;

import com.spring.mood.projectmvc.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {

    boolean save(Member member);

    Member findOne(String userAccount);

    boolean delete(String userAccount);

    int checkId (String userAccount);

    int checkEmail (String userEmail);




}
