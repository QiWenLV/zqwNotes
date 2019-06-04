package com.sort;

import com.utils.RandomArray;
import org.junit.Before;
import org.junit.Test;

/**
 * 冒泡排序
 */
public class BubbleSort {


    @Test
    public void test1(){
        int[] array = RandomArray.getArray(10);
        RandomArray.printArray("原始数组", array);

        RandomArray.printArray("冒泡排序", bubbleSimple(array));
    }

    @Test
    public void test2(){
        int[] array = RandomArray.getArray(10);
        RandomArray.printArray("原始数组", array);

        RandomArray.printArray("优化冒泡排序", bubbleBest(array));
    }


    /**
     * 冒泡简单实现
     */
    public int[] bubbleSimple(int[] array) {

        int temp;

        //外层循环是排序的趟数
        for (int i = 0; i < array.length - 1 ; i++) {

            //内层循环是当前趟数需要比较的次数
            for (int j = 0; j < array.length - i - 1; j++) {

                //前一位与后一位与前一位比较，如果前一位比后一位要大，那么交换
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        return array;
    }


    /**
     * 优化冒泡排序
     * 如果在某躺排序中没有发生交换位置，那么我们可以认为该数组已经排好序了
     */
    public int[] bubbleBest(int[] array) {

        int temp;

        //记录是否发生了置换， 0 表示没有发生置换、 1 表示发生了置换
        boolean isChange = true;

        //外层循环是排序的趟数
        for (int i = 0; i < array.length - 1 ; i++) {
            //每比较一趟就重新初始化为0
            isChange = true;

            //内层循环是当前趟数需要比较的次数
            for (int j = 0; j < array.length - i - 1; j++) {

                //前一位与后一位与前一位比较，如果前一位比后一位要大，那么交换
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    //如果进到这里面了，说明发生置换了
                    isChange = false;
                }
            }
            //如果没有发生置换，代表已经排好序了
            if (isChange){
                break;
            }
        }

        return array;
    }
}

