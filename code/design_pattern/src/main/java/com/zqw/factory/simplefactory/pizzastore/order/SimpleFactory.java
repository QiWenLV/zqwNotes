package com.zqw.factory.simplefactory.pizzastore.order;

import com.zqw.factory.simplefactory.pizzastore.pizza.CheesePizza;
import com.zqw.factory.simplefactory.pizzastore.pizza.GreekPizza;
import com.zqw.factory.simplefactory.pizzastore.pizza.Pizza;

public class SimpleFactory {
    //创建工厂
    public Pizza createPizza(String name){
        Pizza pizza = null;
        if ("cheese".equals(name)) {
            pizza =  new CheesePizza();
            pizza.setName(" 奶酪披萨");
        } else if("greek".equals(name)){
            pizza =  new GreekPizza();
            pizza.setName(" 希腊披萨");
        }
        return pizza;
    }
}
