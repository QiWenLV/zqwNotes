package com.zqw.factory.absfactory.pizzastore.order;

import com.zqw.factory.absfactory.pizzastore.pizza.LDCheesePizza;
import com.zqw.factory.absfactory.pizzastore.pizza.LDPepperPizza;
import com.zqw.factory.absfactory.pizzastore.pizza.Pizza;

/**
 * 伦敦工厂子类
 */
public class LDFactory extends AbsFactory {

    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if ("cheese".equals(orderType)) {
            pizza =  new LDCheesePizza();
        } else if("pepper".equals(orderType)){
            pizza =  new LDPepperPizza();
        }
        return pizza;
    }
}
