package com.spring.mood.projectmvc.service;

import com.spring.mood.projectmvc.entity.User;
import com.spring.mood.projectmvc.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public User getUserByAccount(String userAccount) {
        return userMapper.findByUserAccount(userAccount);
    }
}
