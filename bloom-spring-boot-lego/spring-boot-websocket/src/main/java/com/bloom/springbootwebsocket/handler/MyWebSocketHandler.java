package com.bloom.springbootwebsocket.handler;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author curry
 * Created by on 2022-07-28 下午2:35
 */
public class MyWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        session.sendMessage(new TextMessage("Hello Client"));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message)
            throws Exception {
        System.out.println("Get message from client: " + message.getPayload());
        session.sendMessage(new TextMessage("received： " + message.getPayload()));
    }

}