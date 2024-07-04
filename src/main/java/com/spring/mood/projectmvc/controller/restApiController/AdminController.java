package com.spring.mood.projectmvc.controller.restApiController;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private Integer currentTopicId = 1; // 기본 값 설정
    private final SimpMessagingTemplate messagingTemplate;


    /**
     *
     * @param request - 새로 바뀔 주제의 번호
     * @return - 성공하면 success를 boolean 형태로 보내줌.
     */
    @PostMapping("/updateTopic")
    public Map<String, Object> updateTopic(@RequestBody Map<String, Integer> request) {
        Integer newTopicId = request.get("topicId");


        if (newTopicId != null) {
            currentTopicId = newTopicId; // 현재 토픽
            messagingTemplate.convertAndSend("/topic/currentTopic", Collections.singletonMap("topicId", newTopicId));
        } else {
            throw new IllegalArgumentException("빈 토픽 ㄴㄴ");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        return response;
    }


    /**
     * currentTopicId가 위에서 바꿔준 현재 토픽 번호임.
     * response 객체에 담아서 가져오기
     * @return - 현재 토픽 번호
     */
    @GetMapping("/currentTopic")
    public Map<String, Object> getCurrentTopic() {
        Map<String, Object> response = new HashMap<>();
        response.put("topicId", currentTopicId);
        return response;
    }
}