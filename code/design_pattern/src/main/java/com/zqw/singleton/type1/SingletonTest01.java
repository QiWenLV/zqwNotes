package com.zqw.singleton.type1;

public class SingletonTest01 {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
    }
}

/**
 * 饿汉式(静态变量)
 */
class Singleton {
    //私有化构造
    private Singleton(){}

    private final static Singleton instance = new Singleton();

    public static Singleton getInstance(){
        return instance;
    }
}
