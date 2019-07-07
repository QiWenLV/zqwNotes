package com.zqw.principle.segregation;

import com.zqw.principle.segregation.Interface1;
/**
 * 接口隔离原则 （违背）
 */
public class Segregation1 {
    public static void main(String[] args) {

    }
}


interface Interface1{
    void operation1();
    void operation2();
    void operation3();
    void operation4();
    void operation5();
}

class B implements Interface1 {

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

    @Override
    public void operation4() {
        System.out.println("B 实现 operation4 方法");
    }

    @Override
    public void operation5() {
        System.out.println("B 实现 operation5 方法");
    }
}

class D implements Interface1 {

    @Override
    public void operation1() {
        System.out.println("D 实现 operation1 方法");
    }

    @Override
    public void operation2() {
        System.out.println("D 实现 operation2 方法");
    }

    @Override
    public void operation3() {
        System.out.println("D 实现 operation3 方法");
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
    public void depend1(Interface1 i){
        i.operation1();
        i.operation2();
        i.operation3();
    }
}
// C 类通过接口Interface1 依赖使用D类，但是只会用到1，4，5方法
class C {
    public void depend1(Interface1 i){
        i.operation1();
        i.operation4();
        i.operation5();
    }
}