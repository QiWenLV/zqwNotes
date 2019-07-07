package com.zqw.principle.singleresponsibility;

/**
 * 单一职责原则，方案3（不完全遵守）
 *
 * 没有在类上遵守单一职责原则，但是在方法上是遵守单一职责原则
 */
public class SingleResponsibility3 {

    public static void main(String[] args) {

        Vehicle2 vehicle2 = new Vehicle2();
        vehicle2.run("摩托车");
        vehicle2.runAir("飞机");
        vehicle2.runWater("轮船");
    }
}


class Vehicle2 {
    public void run(String vehicle) {
        System.out.println(vehicle + "公路运行");
    }

    public void runAir(String vehicle) {
        System.out.println(vehicle + "在空中运行");
    }
    public void runWater(String vehicle) {
        System.out.println(vehicle + "在水中运行");
    }
}

