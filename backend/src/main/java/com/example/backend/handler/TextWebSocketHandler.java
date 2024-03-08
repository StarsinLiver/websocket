package com.example.backend.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;

/**
 * packageName : com.example.backend.handler
 * fileName : TextWebSocketHandler
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

@Component
@Slf4j
public class TextWebSocketHandler extends org.springframework.web.socket.handler.TextWebSocketHandler {

    private static List<WebSocketSession> list = new ArrayList<>();

    //    얘도 많이들 사용함
    private Set<WebSocketSession> sessions
            = Collections.synchronizedSet(new HashSet<WebSocketSession>() );

    // Client가 접속 시 호출되는 메서드
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        list.add(session); // 세션 추가
        System.out.println("{} - 클라이언트 접속 : " + session);
        System.out.println("{} - 클라이언트 ID : " + session.getId());
        System.out.println("{} - 클라이언트 프로토클 : " + session.getUri());
        System.out.println(list.size());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("payload : " + payload);
        System.out.println(list.size());

//        접속된 유저에게 보냄
        for (WebSocketSession ws : list) {
            ws.sendMessage(message);
        }
    }

    // Client가 접속 해제 시 호출되는 메서드
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("{} - 클라이언트 접속 해제 : " + session);
        list.remove(session);
    }
}