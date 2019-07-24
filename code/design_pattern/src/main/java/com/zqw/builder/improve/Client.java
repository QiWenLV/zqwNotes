package com.zqw.builder.improve;

/**
 * 建造者模式
 */
public class Client {

    public static void main(String[] args) {
        House build = new HouseDirector(new CommonHouse()).build();
        House build1 = new HouseDirector(new HighHouse()).build();
    }

}
