package com.spring.mood.projectmvc.service;


import com.spring.mood.projectmvc.entity.ChatEntity;
import com.spring.mood.projectmvc.mapper.ChatMapper;
import com.spring.mood.projectmvc.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository repository;

    public ChatEntity saveMessage(ChatEntity message) {
        return repository.save(message);
    }

    public List<ChatEntity> getAllMessages() {
        return repository.findAll();
    }
}
