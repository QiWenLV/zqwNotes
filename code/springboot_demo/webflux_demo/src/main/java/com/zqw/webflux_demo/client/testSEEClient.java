package com.zqw.webflux_demo.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.BodyExtractor;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.ServerSocket;
import java.util.Objects;

/**
 * @Classname testSEEClient
 * @Description TODO
 * @Date 2019/7/26 15:53
 * @Created by zqw
 * @Version 1.0
 */
public class testSEEClient {




    public static void main(String[] args) {
        final WebClient client = WebClient.create();
        client.get()
                .uri("http://localhost:8080/user")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .flatMapMany(response -> response.body(BodyExtractors.toFlux(new ParameterizedTypeReference<ServerSentEvent<String>>() {})))
                .filter(sse -> Objects.nonNull(sse.data()))
                .map(ServerSentEvent::data)
                .buffer(10)
                .doOnNext(System.out::println)
                .blockFirst();
    }
}
