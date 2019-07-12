package com.zqw.singleton.type6;

public class SingletonTest06 {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
    }
}

/**
 * 静态内部类
 */
class Singleton {

    private Singleton(){}
    private static class SingletonInstance {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static synchronized Singleton getInstance(){
        return SingletonInstance.INSTANCE;
    }
}
