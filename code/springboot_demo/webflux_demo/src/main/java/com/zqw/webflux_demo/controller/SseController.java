package com.zqw.webflux_demo.controller;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Classname SseController
 * @Description 长连接测试
 * @Date 2019/7/26 14:38
 * @Created by zqw
 * @Version 1.0
 */
@RestController
@RequestMapping("/see")
public class SseController {

    @GetMapping("/random")
    public Flux<ServerSentEvent<Integer>> random(){
        return Flux.interval(Duration.ofMillis(100))
                .map(seq -> ServerSentEvent.<Integer>builder()
                        .event("random")
                        .id(seq.toString())
                        .data(ThreadLocalRandom.current().nextInt())
                        .build());
    }
}
