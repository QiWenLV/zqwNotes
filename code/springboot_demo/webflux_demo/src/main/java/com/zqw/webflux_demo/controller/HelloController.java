package com.zqw.webflux_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @Classname HelloController
 * @Description TODO
 * @Date 2019/7/26 14:06
 * @Created by zqw
 * @Version 1.0
 */
@RestController
public class HelloController {


    @GetMapping("/hello")
    public Mono<String> sayHello(){
        return Mono.just("Hello World");
    }


}
