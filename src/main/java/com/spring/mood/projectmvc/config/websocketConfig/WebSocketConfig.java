package com.spring.mood.projectmvc.config.websocketConfig;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Controller
@EnableWebSocketMessageBroker
public class WebSocketConfig  implements WebSocketMessageBrokerConfigurer {
    private static final Logger log = LoggerFactory.getLogger(WebSocketConfig.class);


    /** 2번~
     * 클라이언트에서 보낸 메시지를 확인할 땐 /app으로 시작된 값을 보내서 서버에서 값을 확인
     * 서버에서 응답을 보낼 땐 /topic을 통해서 값을 보내줌
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }


    /** 1번 .
     * 클라이언트랑 백엔드에서의 연결을 위해서 /chat-websocket 이라는 요청을 보내면 접속 요청을 보낼 수 있게 함
     * withSockJs는 브라우저에서 지원하지 않아도 SockJS를 통해서 WebSocket과 비슷하게 연결하게 함~
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat-websocket").withSockJS();

    }

}
