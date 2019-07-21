package com.zqw.adapter.objectadapter;

import java.util.Objects;

public class Phone {
    //充电方法
    public void charging(IVoltage5V iVoltage5V){
        if(Objects.nonNull(iVoltage5V) && iVoltage5V.output5V() == 5){
            System.out.println("电压为5V，可以充电");
        } else {
            System.out.println("不能充电");
        }
    }
}
