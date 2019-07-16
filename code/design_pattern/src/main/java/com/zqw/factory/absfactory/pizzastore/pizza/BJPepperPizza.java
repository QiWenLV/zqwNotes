package com.zqw.factory.absfactory.pizzastore.pizza;


public class BJPepperPizza extends Pizza {

    @Override
    public void prepare() {
        setName("北京胡椒披萨");
        System.out.println(" 准备北京胡椒");
    }
}
