package com.example.backend.handler;

import com.example.backend.model.Message;
import com.example.backend.model.Utils;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * packageName : com.example.backend.handler
 * fileName : WebSocketHandler
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

public class WebSocketHandler extends AbstractWebSocketHandler {

//    private static List<WebSocketSession> list = new ArrayList<>();

    //    얘도 많이들 사용함
    private Set<WebSocketSession> sessions
            = Collections.synchronizedSet(new HashSet<>());

    // Client가 접속 시 호출되는 메서드
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session); // 세션 추가
        System.out.println("{} - 클라이언트 접속 : " + session);
        System.out.println("{} - 클라이언트 ID : " + session.getId());
        System.out.println("{} - 클라이언트 프로토클 : " + session.getUri());
        System.out.println(sessions.size());
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        System.out.println("이곳은 바이너리 데이터를 받는 곳입니다.");
        byte[] payload = message.getPayload().array();
        System.out.println("{} = payload : " + payload.length);

//        접속된 유저에게 보냄
        for (WebSocketSession ws : sessions) {
            ws.sendMessage(new BinaryMessage(payload));
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        Message message = Utils.getObject(textMessage.getPayload(), Message.class);
        message.setSender(session.getId());

//        접속된 유저에게 보냄
        for (WebSocketSession ws : sessions) {
            ws.sendMessage(new TextMessage(Utils.getString(message)));
        }
    }

    // Client가 접속 해제 시 호출되는 메서드
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("{} - 클라이언트 접속 해제 : " + session);
        System.out.println("오류 메시지(이유) : " + status);
        sessions.remove(session);
    }
}