package com.zqw.factory.factorymethod.pizzastore.order;


import com.zqw.factory.factorymethod.pizzastore.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * 披萨订单
 */
public abstract class OrderPizza {

    abstract Pizza cratetePizza(String orderTpye);

    //构造器
    public OrderPizza() {
        Pizza pizza = null;
        String orderType;
        do {
            orderType = getType();
            //具体实现由子类完成
            pizza = cratetePizza(orderType);
            //输出pizza
            if(Objects.nonNull(pizza)){
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.bake();
            } else {
                System.out.println(" 订购披萨失败");
            }
        } while (true);
    }

    /**
     * 获取用户输入
     * @return
     */
    public String getType(){
        try {
            BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\n输入需要的pizza：");
            String srt = strin.readLine();
            return srt;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
