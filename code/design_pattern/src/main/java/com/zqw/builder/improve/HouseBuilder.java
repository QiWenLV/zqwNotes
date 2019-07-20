package com.zqw.builder.improve;

public abstract class HouseBuilder {

    protected House house = new House();

    //建造流程
    //地基
    public abstract void buildBasic();
    //砌墙
    public abstract void buildWalls();
    //房顶
    public abstract void roofed();

    //返回产品
    public House buildHouse(){
        return house;
    }
}
