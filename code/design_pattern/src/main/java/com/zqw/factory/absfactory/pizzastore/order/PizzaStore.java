package com.zqw.factory.absfactory.pizzastore.order;


public class PizzaStore {

    public static void main(String[] args) {
        new OrderPizza(new BJFactory());
        System.out.println("退出程序~");
    }
}
