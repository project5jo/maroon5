package com.spring.mood.projectmvc.mapper;

import com.spring.mood.projectmvc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM Users WHERE user_account = #{userAccount}")
    User findByUserAccount(String userAccount);
}
