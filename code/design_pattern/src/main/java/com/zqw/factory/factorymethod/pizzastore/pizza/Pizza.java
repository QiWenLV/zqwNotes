package com.zqw.factory.factorymethod.pizzastore.pizza;

/**
 * 披萨抽象类
 */
public abstract class Pizza {

    protected String name;  //名字

    //准备原材料，不同的披萨是不一样的
    public abstract void prepare();

    //烘烤
    public void bake(){
        System.out.println(name + "baking;");
    }
    //切割
    public void cut(){
        System.out.println(name + "cutting;");
    }
    //打包
    public void box(){
        System.out.println(name + "boxing;");
    }

    public void setName(String name){
        this.name = name;
    }
}
