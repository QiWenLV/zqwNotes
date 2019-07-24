package com.zqw.builder.ordinary;

public abstract class AbstractHouse {

    //地基
    public abstract void buildBasic();
    //砌墙
    public abstract void buildWalls();
    //房顶
    public abstract void roofed();

    public void build(){
        buildBasic();
        buildWalls();
        roofed();
    }
}
