package com.zqw.adapter.interfaceadaper;

public class Client{

    public static void main(String[] args) {

        AbsAdapter absAdapter = new AbsAdapter() {
            @Override
            public void m1() {
                System.out.println("我只想使用m1方法");
            }
        };
    }

}
