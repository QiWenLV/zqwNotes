package com.zqw.principle.singleresponsibility;

/**
 * 单一职责原则，方案2（遵守）
 * 但是这样如果新加功能，改动会很大。重复代码也很多
 *
 * 直接改进Vehicle类，修改代码会比较少
 */
public class SingleResponsibility2 {

    public static void main(String[] args) {

        RoadVehicle roadVehicle = new RoadVehicle();
        roadVehicle.run("摩托车");
        roadVehicle.run("汽车");

        AirVehicle airVehicle = new AirVehicle();
        airVehicle.run("飞机");
    }
}



class RoadVehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + "公路运行");
    }
}

class AirVehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + "空中运行");
    }
}