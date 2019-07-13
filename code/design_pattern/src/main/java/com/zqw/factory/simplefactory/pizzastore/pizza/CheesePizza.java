package com.zqw.factory.simplefactory.pizzastore.pizza;

public class CheesePizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println(" 准备奶酪");
    }
}
