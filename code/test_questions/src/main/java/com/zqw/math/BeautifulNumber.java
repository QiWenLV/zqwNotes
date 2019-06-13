package com.zqw.math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 求一个数的最美数字
 * 最美数字就是指 10进制的某个数转化为N进制后为 111...
 * 例如：33 结果就是 32进制 两个1
 */
public class BeautifulNumber {

    public static void main(String[] args) {

        int num = 33;
        //计算时用的临时变量
        int tmp;
        //统计转为i进制之后的位数
        int count = 0;
        //存放符合要求的结果
        Map<Integer, Integer> results = new HashMap<>();

        //进制循环
        for(int i = 2; i < num; i++){
            tmp = num;
            //取余循环
            while (tmp > 0){
                int m = tmp % i;
                if(m == 1){
                    tmp = tmp / i;
                    count ++;
                } else {
                    tmp = -1;
                    count = 0;
                }
            }
            if (tmp == 0){
                results.put(i, count);
                count = 0;
            }
        }

        results.forEach((key, value) -> System.out.println(key + "--" + value));

    }
}
