package com.zqw.builder.improve;

public class HouseDirector {

    HouseBuilder houseBuilder = null;

    //构造器传入
    public HouseDirector(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    public House build(){
        houseBuilder.buildBasic();
        houseBuilder.buildWalls();
        houseBuilder.roofed();
        return houseBuilder.buildHouse();
    }
}
