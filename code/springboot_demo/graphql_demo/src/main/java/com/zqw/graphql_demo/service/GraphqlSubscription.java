package com.zqw.graphql_demo.service;

import com.zqw.graphql_demo.Person;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLSubscription;

import io.leangen.graphql.spqr.spring.annotation.GraphQLApi;
import io.leangen.graphql.spqr.spring.util.ConcurrentMultiRegistry;
import org.reactivestreams.Publisher;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Classname GraphqlSubscription
 * @Description TODO
 * @Date 2019/7/26 10:21
 * @Created by zqw
 * @Version 1.0
 */
@Service
@GraphQLApi
public class GraphqlSubscription {

    private final ConcurrentMultiRegistry<String, FluxSink<Person>> subscribers = new ConcurrentMultiRegistry<>();


    public List<Person> init(){
        List<Person> people = new ArrayList<>();

        people.add(new Person("aaa", 1, LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE)));
        people.add(new Person("bbb", 2, LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE)));
        people.add(new Person("ccc", 3, LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE)));
        people.add(new Person("ddd", 4, LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE)));
        return people;
    }

    @GraphQLQuery(name = "queryPersion", description = "查询列表")
    public List<Person> queryPersion(@GraphQLArgument(name = "person") Person person){
        return init();
    }

    @GraphQLMutation(name = "updatePerson", description = "修改")
    public Integer updatePerson(@GraphQLArgument(name = "person") Person person){
        return 1;
    }

    //长连接
    @GraphQLSubscription(name = "queryLongPersion", description = "查询列表")
    public Flux<ServerSentEvent<Integer>> queryLongPersion(@GraphQLArgument(name = "person") Person person){
        System.out.println("访问长连接");
//        return Flux.create(subscriber ->
//                subscribers.add(person.getName(), subscriber.onDispose(() -> subscribers.remove(person.getName(), subscriber))),
//                FluxSink.OverflowStrategy.LATEST);

        return Flux.interval(Duration.ofSeconds(1))
                .map(seq -> ServerSentEvent.<Integer>builder()
                        .event("random")
                        .id(seq.toString())
                        .data(ThreadLocalRandom.current().nextInt())
                        .build());
    }
}


