package com.zqw.factory.absfactory.pizzastore.pizza;


public class LDCheesePizza extends Pizza {
    @Override
    public void prepare() {
        setName("伦敦奶酪披萨");
        System.out.println(" 准备伦敦奶酪");
    }
}
