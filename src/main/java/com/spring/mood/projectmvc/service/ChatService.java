package com.spring.mood.projectmvc.service;


import com.spring.mood.projectmvc.entity.ChatEntity;
import com.spring.mood.projectmvc.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatRepository repository;

    public ChatEntity saveMessage(ChatEntity message) {
        return repository.save(message);
    }

    public List<ChatEntity> getAllMessages() {
        return repository.findAll();
    }
}
