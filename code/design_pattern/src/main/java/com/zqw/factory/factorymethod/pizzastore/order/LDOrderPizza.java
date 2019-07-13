package com.zqw.factory.factorymethod.pizzastore.order;


import com.zqw.factory.factorymethod.pizzastore.pizza.*;

public class LDOrderPizza extends OrderPizza {
    @Override
    Pizza cratetePizza(String orderTpye) {
        Pizza pizza = null;
        if ("cheese".equals(orderTpye)) {
            pizza = new LDCheesePizza();
        } else if("pepper".equals(orderTpye)){
            pizza = new LDPepperPizza();
        }
        return pizza;
    }
}
