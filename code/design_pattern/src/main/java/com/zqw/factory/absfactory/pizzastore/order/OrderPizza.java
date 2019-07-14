package com.zqw.factory.absfactory.pizzastore.order;


import com.zqw.factory.absfactory.pizzastore.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class OrderPizza {

    private AbsFactory absFactory;
    private Pizza pizza = null;

    //构造器(聚合)
    public OrderPizza(AbsFactory absFactory) {
        setSimpleFactory(absFactory);
    }

    public void setSimpleFactory(AbsFactory absFactory){
        String orderType = "";
        this.absFactory = absFactory;

        do {
            orderType = getType();
            pizza = this.absFactory.createPizza(orderType);
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
