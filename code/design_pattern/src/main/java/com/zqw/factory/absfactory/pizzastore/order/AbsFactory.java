package com.zqw.factory.absfactory.pizzastore.order;

import com.zqw.factory.absfactory.pizzastore.pizza.Pizza;

/**
 * 抽象工厂模式的抽象层
 */
public abstract class AbsFactory {
    public abstract Pizza createPizza(String orderType);
}
