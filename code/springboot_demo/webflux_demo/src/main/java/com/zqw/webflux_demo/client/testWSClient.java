package com.zqw.webflux_demo.client;

import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.time.Duration;

/**
 * @Classname testWSClient
 * @Description TODO
 * @Date 2019/7/26 16:01
 * @Created by zqw
 * @Version 1.0
 */
public class testWSClient {
    public static void main(String[] args) {

        WebSocketClient client = new ReactorNettyWebSocketClient();
        client.execute(URI.create("ws://localhost:8080/echo"),
                session -> session.send(Flux.just(session.textMessage("Hello")))
                        .thenMany(session.receive().take(1).map(WebSocketMessage::getPayloadAsText))
                        .doOnNext(System.out::println)
                        .then())
                .block(Duration.ofMillis(5000));
    }

}
