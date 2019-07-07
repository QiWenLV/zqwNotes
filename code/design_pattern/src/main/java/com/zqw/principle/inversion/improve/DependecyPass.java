package com.zqw.principle.inversion.improve;

/**
 * 依赖传递的三种方式
 */
public class DependecyPass {
    public static void main(String[] args) {
        //方式1
        OpenAndClose1 derail1 = new OpenAndClose1();
        derail1.open(() -> System.out.println("长虹电视1"));

        //方式2
        OpenAndClose2 derail2 = new OpenAndClose2(() -> System.out.println("长虹电视2"));
        derail2.open();

        //方式3
        OpenAndClose3 derail3 = new OpenAndClose3();
        derail3.setTv(() -> System.out.println("长虹电视3"));
        derail3.open();
    }
}

//ITV接口
interface ITV {
    public void play();
}

//方式1：通过接口传递实现依赖
//开关接口
interface IOpenAndClose1{
    public void open(ITV tv);   //抽象方法接收接口
}

//实现接口
class OpenAndClose1 implements IOpenAndClose1 {
    @Override
    public void open(ITV tv) {
        tv.play();
    }
}

//方式2：通过构造方法依赖传递
interface IOpenAndClose2{
    public void open();   //抽象方法
}
//实现接口
class OpenAndClose2 implements IOpenAndClose2 {
    private ITV tv;

    //构造器
    public OpenAndClose2(ITV tv) {
        this.tv = tv;
    }
    @Override
    public void open() {
        tv.play();
    }
}

//方法3：通过setter方法依赖传递
interface IOpenAndClose3{
    public void open();   //抽象方法
    public void setTv(ITV tv);
}
//实现接口
class OpenAndClose3 implements IOpenAndClose3{
    private ITV tv;
    @Override
    public void open() {
        this.tv.play();
    }
    @Override
    public void setTv(ITV tv) {
        this.tv = tv;
    }
}