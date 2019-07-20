package com.zqw.builder.improve;

public class HighHouse extends HouseBuilder{
    @Override
    public void buildBasic() {
        System.out.println("高楼地基100米");
    }

    @Override
    public void buildWalls() {
        System.out.println("高楼的墙1000米");
    }

    @Override
    public void roofed() {
        System.out.println("高楼的透明屋顶");
    }
}
