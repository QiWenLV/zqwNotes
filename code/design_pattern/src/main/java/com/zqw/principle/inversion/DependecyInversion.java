package com.zqw.principle.inversion;

/**
 * 依赖倒转原则 (违背)
 *
 * 如果要扩展接收微信，短信等，需要新增类，不合理
 *
 * 改进思路：引入一个抽象接口，表示接收者
 */
public class DependecyInversion {

    public static void main(String[] args) {
        Person person = new Person();
        person.receive(new Email());
    }
}

class Email{
    public String getInfo(){
        return "电子邮件信息：hello, world";
    }
}

class Person {

    public void receive(Email email){
        System.out.println(email.getInfo());
    }
}
