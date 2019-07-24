package com.zqw.bridge;

public class UpRightPhone extends Phone{

    public UpRightPhone(Brand brand) {
        super(brand);
    }
    protected void open(){
        super.open();
        System.out.println("旋转样式的手机开机");
    }
    protected void close(){
        super.close();
        System.out.println("旋转样式的手机关机");;
    }
    protected void call(){
        super.call();
        System.out.println("旋转样式的手机打电话");
    }
}
