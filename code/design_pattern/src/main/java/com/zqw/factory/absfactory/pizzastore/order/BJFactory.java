package com.zqw.factory.absfactory.pizzastore.order;

import com.zqw.factory.absfactory.pizzastore.pizza.BJCheesePizza;
import com.zqw.factory.absfactory.pizzastore.pizza.BJPepperPizza;
import com.zqw.factory.absfactory.pizzastore.pizza.Pizza;

/**
 * 北京工厂子类
 */
public class BJFactory extends AbsFactory {

    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if ("cheese".equals(orderType)) {
            pizza =  new BJCheesePizza();
        } else if("pepper".equals(orderType)){
            pizza =  new BJPepperPizza();
        }
        return pizza;
    }
}
