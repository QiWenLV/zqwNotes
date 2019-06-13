package com.zqw.utils;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class RunTimeTools<T> {

    public static RunTimeTools getInstance(){
        return new RunTimeTools();
    }

    public T time(Supplier<T> supplier, String detail){
        long start = System.currentTimeMillis();
        T t = supplier.get();
        long end = System.currentTimeMillis();
        System.out.println(detail + "执行时间：" + (end - start) + "ms");
        return t;
    }

    public T time(Supplier<T> supplier){
        long start = System.currentTimeMillis();
        T t = supplier.get();
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - start) + "ms");
        return t;
    }
}

//interface CallBack{
//    void execute();
//}
