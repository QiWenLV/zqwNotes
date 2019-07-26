package com.zqw.webflux_demo.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

/**
 * @Classname EchoHandler
 * @Description WebSocket 测试
 * @Date 2019/7/26 14:49
 * @Created by zqw
 * @Version 1.0
 */
@Component
public class EchoHandler implements WebSocketHandler {

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.send(session.receive().map(msg -> session.textMessage("Echo -> " + msg.getPayloadAsText())));
    }
}
