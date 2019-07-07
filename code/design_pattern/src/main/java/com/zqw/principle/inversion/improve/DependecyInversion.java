package com.zqw.principle.inversion.improve;

/**
 * 依赖倒转原则 (遵守)
 *
 * 增加一个公共接口，客户端都不需要改变。
 */
public class DependecyInversion {

    public static void main(String[] args) {
        Person person = new Person();
        person.receive(new Email());
        person.receive(new WeiXin());
    }
}

//定义一个接口
interface IReceiver {
    public String getInfo();
}

class Email implements IReceiver{
    @Override
    public String getInfo(){
        return "电子邮件信息：hello, world";
    }
}
class WeiXin implements IReceiver {

    @Override
    public String getInfo() {
        return "微信信息：hello, ok";
    }
}

class Person {

    public void receive(IReceiver i){
        System.out.println(i.getInfo());
    }
}
