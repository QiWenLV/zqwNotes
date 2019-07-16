package com.zqw.factory.simplefactory.pizzastore.order;

import com.zqw.factory.simplefactory.pizzastore.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * 披萨订单
 */
public class OrderPizza {

    private SimpleFactory simpleFactory;
    private Pizza pizza = null;

    //构造器(聚合)
    public OrderPizza(SimpleFactory simpleFactory) {
        setSimpleFactory(simpleFactory);
    }

    public void setSimpleFactory(SimpleFactory simpleFactory){
        String orderType = "";
        this.simpleFactory = simpleFactory;

        do {
            orderType = getType();
            pizza = this.simpleFactory.createPizza(orderType);

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
