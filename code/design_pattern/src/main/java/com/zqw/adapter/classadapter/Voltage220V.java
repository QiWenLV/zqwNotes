package com.zqw.adapter.classadapter;

//被适配的类
public class Voltage220V {

    public int output220V(){
        int src = 220;
        System.out.println("输出电压为 " + src + "V");
        return 220;
    }
}
