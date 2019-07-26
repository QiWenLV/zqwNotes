package com.zqw.webflux_demo.client;

import com.zqw.webflux_demo.User;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

/**
 * @Classname testRESTClient
 * @Description TODO
 * @Date 2019/7/26 15:40
 * @Created by zqw
 * @Version 1.0
 */
public class testRESTClient {

    public static void main(String[] args) throws InterruptedException {

        User user = new User(4, "赵六", "123");
        WebClient client = WebClient.create("http://localhost:8080/user");

        Flux<User> createUser = client.post()
                .uri("")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), User.class)
                .retrieve()
                .bodyToFlux(User.class);
        createUser.subscribe(System.out::println);


        Mono<User> userMono = client.get()
                .uri("")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(User.class);
        userMono.subscribe(System.out::println);

        TimeUnit.SECONDS.sleep(1);
    }
}
