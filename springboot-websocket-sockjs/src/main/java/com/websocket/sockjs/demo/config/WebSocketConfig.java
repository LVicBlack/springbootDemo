package com.websocket.sockjs.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 应用程序以 /app 为前缀，而 代理目的地以 /topic 为前缀.
        // js.url = "/spring13/app/hello" -> @MessageMapping("/hello") 注释的方法.
        config.enableSimpleBroker("/topic", "/queue");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 在网页上我们就可以通过这个链接 /server/hello ==<c:url value='/hello'></span> 来和服务器的WebSocket连接
        registry.addEndpoint("/hello").withSockJS();
    }
}
