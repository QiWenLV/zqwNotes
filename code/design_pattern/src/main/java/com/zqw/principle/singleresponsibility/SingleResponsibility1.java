package com.zqw.principle.singleresponsibility;

/**
 * 单一职责原则，方案一（违背）
 */
public class SingleResponsibility1 {

    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        vehicle.run("摩托车");
        vehicle.run("飞机");
        /**
         * 以上打印结果违背了单一职责原则。run方法既管理陆地交通工具，又管理其他飞机。
         * 应该将交通工具分解为不同的类
         */
    }
}

//交通工具类
class Vehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + "公路运行");
    }
}