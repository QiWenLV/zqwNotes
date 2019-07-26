package com.zqw.webflux_demo.controller;

import com.zqw.webflux_demo.User;
import org.omg.CORBA.UserException;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname UserController
 * @Description 普通请求测试
 * @Date 2019/7/26 14:13
 * @Created by zqw
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final Map<Integer, User> data = new ConcurrentHashMap<>();

    @PostConstruct
    private void init(){
        data.put(1, new User(1, "张三", "123"));
        data.put(2, new User(2, "李四", "123"));
        data.put(3, new User(3, "王五", "123"));
    }

    @GetMapping("")
    public Flux<User> list(){
        return Flux.fromIterable(data.values());
    }

    @PutMapping()
    public Flux<User> getByIds(Flux<Integer> ids){
        return ids.flatMap(id -> Mono.justOrEmpty(data.get(id)));
    }

    @PostMapping
    public Flux<User> creatOrUpdate(@RequestBody Flux<User> users){
        return users.doOnNext(user -> data.put(user.getId(), user));
    }
    @DeleteMapping("/{id}")
    public Mono<User> delete(@PathVariable Integer id){
        return Mono.justOrEmpty(data.remove(id));
    }
}
