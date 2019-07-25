package com.zqw.test.rxjava_demo;

import io.reactivex.Observable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname RxJavaDemo2
 * @Description TODO
 * @Date 2019/7/25 15:58
 * @Created by zqw
 * @Version 1.0
 */
public class RxJavaDemo2 {



    public static void main(String[] args) {
        //需求：找出每个Person的plan列表中的action
        List<Person> personList = new ArrayList<>();
        Observable.fromIterable(personList)
                .flatMap(x -> Observable.fromIterable(x.getPlanList()))
                .flatMap(x -> Observable.fromIterable(x .getActionList()))
                .subscribe(System.out::println);

        //flatMap: 整合加工，返回一个新事件源。

        //concatMap: concatMap() 和 flatMap() 基本上是一样的，只不过 concatMap() 转发出来的事件是有序的，而 flatMap() 是无序的。

    }


    @Data
    private class Person {
        private String name;
        private List<Plan> planList = new ArrayList<>();
    }

    @Data
    private class Plan {
        private String time;
        private String content;
        private List<String> actionList = new ArrayList<>();
    }
}
