package com.zqw.principle.segregation.improve;

/**
 * 接口隔离原则 （遵守）
 *
 * 将原本的一个接口拆分为三个接口
 */
public class Segregation2 {
    public static void main(String[] args) {
        A a = new A();
        a.depend(new B(), new B());

    }
}

//接口1
interface Interface1{
    void operation1();
}
//接口2
interface Interface2{
    void operation2();
    void operation3();
}
//接口3
interface Interface3{
    void operation4();
    void operation5();
}

class B implements Interface1, Interface2 {

    @Override
    public void operation1() {
        System.out.println("B 实现 operation1 方法");
    }

    @Override
    public void operation2() {
        System.out.println("B 实现 operation2 方法");
    }

    @Override
    public void operation3() {
        System.out.println("B 实现 operation3 方法");
    }
}

class D implements Interface1, Interface3 {

    @Override
    public void operation1() {
        System.out.println("D 实现 operation1 方法");
    }

    @Override
    public void operation4() {
        System.out.println("D 实现 operation4 方法");
    }

    @Override
    public void operation5() {
        System.out.println("D 实现 operation5 方法");
    }
}

// A 类通过接口Interface1 依赖使用B类，但是只会用到1，2，3方法
class A {
    public void depend(Interface1 i, Interface2 i2){
        i.operation1();
        i2.operation2();
        i2.operation3();
    }
}

// C 类通过接口Interface1 依赖使用D类，但是只会用到1，4，5方法
class C {
    public void depend(Interface1 i, Interface3 i3){
        i.operation1();
        i3.operation4();
        i3.operation5();
    }
}