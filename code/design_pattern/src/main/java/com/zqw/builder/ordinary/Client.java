package com.zqw.builder.ordinary;

/**
 * 建造者模式
 */
public class Client {

    public static void main(String[] args) {
        CommonHouse commonHouse = new CommonHouse();
        commonHouse.build();
    }

}
