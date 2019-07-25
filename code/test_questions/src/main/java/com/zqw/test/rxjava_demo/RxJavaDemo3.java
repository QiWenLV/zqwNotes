package com.zqw.test.rxjava_demo;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.GroupedObservable;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @Classname RxJavaDemo3
 * @Description TODO
 * @Date 2019/7/25 16:21
 * @Created by zqw
 * @Version 1.0
 */
@Slf4j
public class RxJavaDemo3 {

    public static void main(String[] args) {



        //buffer，从需要发送的事件当中获取一定数量的事件，并将这些事件放到缓冲区当中一并发出。
        //count 缓冲区元素的数量，skip 就代表缓冲区满了之后，发送下一次事件序列的时候要跳过多少元素。
        System.out.println("\n-------------------------buffer");
        Observable.just(1, 2, 3, 4, 5)
                .buffer(2, 1)
                .subscribe(x -> {
                    StringJoiner sj = new StringJoiner(",", " 元素：[", "]\n");
                    x.stream().map(String::valueOf)s.forEach(sj::add);
                    System.out.print("缓冲区大小：" + x.size() + sj.toString());
                });

        // groupBy
        //在 groupBy() 方法返回的参数是分组的名字，每返回一个值，那就代表会创建一个组
        System.out.println("\n-------------------------groupBy");
        Observable.just(5, 2, 3, 4, 1, 6, 8, 9, 7, 10)
                .groupBy(x -> x % 3)
                .subscribe(x -> x.subscribe(y -> System.out.println("组名:" + x.getKey() + " 值：" + y)));

        // scan 将数据以一定的逻辑聚合起来。
        System.out.println("\n-------------------------scan");
        Observable.just(1, 2, 3, 4, 5)
                .scan(Integer::sum)
                .subscribe(System.out::println);

        //window 发送指定数量的事件时，就将这些事件分为一组。
        //参数count就是代表指定的数量，例如将 count 指定为2，那么每发2个数据就会将这2个数据分成一组。
        System.out.println("\n-------------------------window");
        Observable.just(1, 2, 3, 4, 5)
                .window(2)
                .subscribe(x -> x.subscribe(System.out::println));

        //collect 发送指定数量的事件时，就将这些事件分为一组。
        System.out.println("\n-------------------------collect");
        Observable.just(1, 2, 3, 4)
                .collect(ArrayList::new, ArrayList::add)
                .subscribe(x -> x.forEach(System.out::println));

        //all 判断事件序列是否全部满足某个事件，如果都满足则返回 true，反之则返回 false。
        System.out.println("\n-------------------------all");
        Observable.just(1, 2, 3, 4)
                .all(x -> x < 4)
                .subscribe(System.out::println);

        //takeWhile 以设置条件，当某个数据满足条件时就会发送该数据，反之则不发送。
        System.out.println("\n-------------------------takeWhile");
        Observable.just(1, 2, 3, 4)
                .takeWhile(x -> x < 4)
                .subscribe(System.out::println);
    }
}
