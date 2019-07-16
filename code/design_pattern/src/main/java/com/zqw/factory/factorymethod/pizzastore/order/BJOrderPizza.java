package com.zqw.factory.factorymethod.pizzastore.order;


import com.zqw.factory.factorymethod.pizzastore.pizza.BJCheesePizza;
import com.zqw.factory.factorymethod.pizzastore.pizza.BJPepperPizza;
import com.zqw.factory.factorymethod.pizzastore.pizza.Pizza;

public class BJOrderPizza extends OrderPizza {
    @Override
    Pizza cratetePizza(String orderTpye) {
        Pizza pizza = null;
        if ("cheese".equals(orderTpye)) {
            pizza = new BJCheesePizza();
        } else if("pepper".equals(orderTpye)){
            pizza = new BJPepperPizza();
        }
        return pizza;
    }
}
