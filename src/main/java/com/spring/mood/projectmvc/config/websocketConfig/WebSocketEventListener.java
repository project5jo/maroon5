package com.spring.mood.projectmvc.config.websocketConfig;

import com.spring.mood.projectmvc.service.ChatService;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Component
public class WebSocketEventListener {

    private final ChatService chatService;
    private final Map<String, HttpSession> sessionMap = new HashMap<>();

    public WebSocketEventListener(ChatService chatService) {
        this.chatService = chatService;
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionSubscribeEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String destination = headerAccessor.getDestination();
        HttpSession session = (HttpSession) headerAccessor.getSessionAttributes().get("HTTP.SESSION.ID");

        if (destination != null && destination.startsWith("/topic/messages/")) {
            String[] split = destination.split("/");
            if (split.length == 5) {
                try {
                    int topicId = Integer.parseInt(split[3]);
                    int roomId = Integer.parseInt(split[4]);
                    chatService.incrementCurrentUsers(topicId, roomId);
                    sessionMap.put(headerAccessor.getSessionId(), session);
                } catch (NumberFormatException e) {
                    // Log the error or handle it accordingly
                }
            }
        }
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        HttpSession session = sessionMap.remove(headerAccessor.getSessionId());
        if (session != null) {
            chatService.decrementCurrentUsers(session);
        }
    }
}