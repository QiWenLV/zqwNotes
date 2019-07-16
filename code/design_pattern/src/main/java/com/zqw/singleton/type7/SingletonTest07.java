package com.zqw.singleton.type7;

public class SingletonTest07 {
    public static void main(String[] args) {
        Singleton instance = Singleton.INSTANCE;
    }
}

/**
 * 枚举
 */
enum Singleton {
    INSTANCE;

    public void something(){
        System.out.println("something...");
    }

}
