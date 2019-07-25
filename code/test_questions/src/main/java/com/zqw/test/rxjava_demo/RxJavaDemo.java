package com.zqw.test.rxjava_demo;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
 * @Classname RxJavaDemo
 * @Description TODO
 * @Date 2019/7/25 12:38
 * @Created by zqw
 * @Version 1.0
 */
@Slf4j
public class RxJavaDemo {

    //defer需要用
    static Integer i = 100;

    public static void main(String[] args) {

        Flowable.just("Hello world").subscribe(System.out::println);


        //create 事件源
        System.out.println("\n-------------------------create");
        Observable<Object> observable = Observable.create(x -> x.onNext("Hello Observer"));
        // 进行消费（传入的是观察者）
        observable.subscribe(System.out::println);


        //just 创建事件源，并发送事件， 发送的事件不可以超过10个以上
        System.out.println("\n-------------------------just");
        Observable.just(1, 2, 3, 4, 5)
                .subscribe(x -> System.out.println("onNext" + x));

        //From操作符
        //fromArray， 和just功能相同，不限制10个事件
        System.out.println("\n-------------------------fromArray");
        Integer[] array = {1, 2, 3, 4};
        Observable.fromArray(array)
                .subscribe(x -> System.out.println("fromArray - onNext" + x));


        //fromCallable， 和Runnable 功能相同。但是会有一个返回值，回调给观察者
        System.out.println("\n-------------------------fromCallable");
        Observable.fromCallable(() -> 1)
                .subscribe(x -> System.out.println("回调结果: " + x));

        //fromFuture， 和Future 功能相同。增加了cancel()等方法操作 Callable。get() 方法来获取 Callable 返回的值
        System.out.println("\n-------------------------fromFuture");

        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println("运行业务逻辑");
            return "操作完成";
        });

        Observable.fromFuture(futureTask, 1000, TimeUnit.MINUTES)
                .doOnSubscribe(x -> futureTask.run())       //doOnSubscribe() 的作用就是只有订阅时才会发送事件
                .subscribe(x -> System.out.println("收到结果：" + x));


        //fromIterable， 直接发送一个 List 集合数据给观察者
        System.out.println("\n-------------------------fromIterable");
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4);
        Observable.fromIterable(list)
                .subscribe(x -> System.out.println("onNext " + x),
                        e -> System.out.println("error " + e),
                        () -> System.out.println("终于完成了"));

        //defer， 这个方法的作用就是直到事件源被订阅后才会创建真的创建事件源。
        System.out.println("\n-------------------------defer");
        Observable<Integer> defer = Observable.defer(() -> Observable.just(i));
        defer.subscribe(x -> System.out.println("onNext: " + x));
        i = 300;
        defer.subscribe(x -> System.out.println("onNext: " + x));

        //timer，当到指定时间后就会发送一个 0L 的值给观察者。
        System.out.println("\n-------------------------timer");
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(x -> log.info("timer: " + x));

        //interval， 每隔一段时间就会发送一个事件，这个事件是从0开始，不断增1的数字。
        System.out.println("\n-------------------------interval");
        Observable.interval(4, TimeUnit.SECONDS)
                .subscribe(x -> log.info("interval: " + x));
        //interval() 第三个方法的 initialDelay 参数，这个参数的意思就是 onSubscribe 回调之后，再次回调 onNext 的间隔时间。

        //intervalRange， 可以指定发送事件的开始值和数量，其他与 interval() 的功能一样。
        System.out.println("\n-------------------------intervalRange");
        Observable.intervalRange(2, 5, 2, 1, TimeUnit.SECONDS)
                .subscribe(x -> log.info("intervalRange: " + x));

        //range 同时发送一定范围的事件序列。
        System.out.println("\n-------------------------range");
        Observable.range(2, 5)
                .subscribe(x -> log.info("range: " + x));

        //empty() ： 直接发送 onComplete() 事件
        //never()：不发送任何事件
        //error()：发送 onError() 事件

        while (true){

        }
    }



}
