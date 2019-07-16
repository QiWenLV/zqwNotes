package com.zqw.prototype;

public class Client {

    public static void main(String[] args) {

        Sheep sheep = new Sheep("tom", 1, "白色");
        sheep.setFriend(new Sheep("jack", 2, "黑色"));
        Sheep clone = (Sheep) sheep.clone();
        System.out.println(sheep);
        System.out.println(clone);
        System.out.println("---------------");
        System.out.println(sheep == clone);
        System.out.println(sheep.getFriend() == clone.getFriend());
    }
}
