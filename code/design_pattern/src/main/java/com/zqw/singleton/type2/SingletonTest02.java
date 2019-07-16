package com.zqw.singleton.type2;

public class SingletonTest02 {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
    }
}

/**
 * 饿汉式(静态代码块)
 */
class Singleton {
    //私有化构造
    private Singleton(){}
    private static Singleton instance;

    static {
        instance = new Singleton();
    }
    public static Singleton getInstance(){
        return instance;
    }
}
