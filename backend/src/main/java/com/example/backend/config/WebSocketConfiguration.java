package com.example.backend.config;

import com.example.backend.handler.TextWebSocketHandler;
import com.example.backend.handler.WebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

/**
 * packageName : com.example.backend.config
 * fileName : WebSocketConfiguration
 * author : san26
 * date : 2024-03-08
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2024-03-08         san26          최초 생성
 */
@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
                .addHandler(getWebSocketHandler(), "/binary")
                .addHandler(getTextWebSocketHandler(), "/text")
                .setAllowedOrigins("*");
    }

    @Bean
    public TextWebSocketHandler getTextWebSocketHandler() {
        return new TextWebSocketHandler();
    }

    @Bean
    public WebSocketHandler getWebSocketHandler() {
        return new WebSocketHandler();
    }


    /**
     * setMaxBinaryMessageBufferSize 메서드를 사용하여 버퍼 크기를 조정할 수 있습니다.
     *
     * @return
     */
    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxBinaryMessageBufferSize(1024 * 1024); // 1MB, adjust as needed
        return container;
    }
}