package com.sort;

import com.utils.RandomArray;
import org.junit.Test;

/**
 * 插入排序
 */
public class InsertionSort {


    @Test
    public void test1(){
        int[] array = RandomArray.getArray(10);
        RandomArray.printArray("原始数组", array);

        RandomArray.printArray("插入排序", insertionSimple(array));
    }

    /**
     * 基础的插入排序
     * @param arr
     * @return
     */
    public int[] insertionSimple(int[] arr){

        int temp;

        //外层循环控制需要排序的趟数(从1开始因为将第0位看成了有序数据)
        for (int i = 1; i < arr.length; i++) {
            temp = arr[i];

            //如果前一位(已排序的数据)比当前数据要大，那么就进入循环比较[参考第二趟排序]
            int j = i - 1;
            while (j >= 0 && arr[j] > temp) {

                //往后退一个位置，让当前数据与之前前位进行比较
                arr[j + 1] = arr[j];
                //不断往前，直到退出循环
                j--;
            }
            //退出了循环说明找到了合适的位置了，将当前数据插入合适的位置中
            arr[j + 1] = temp;
        }
        return arr;
    }


    /**
     * 插入排序优化，采用二分法寻找合适的位置，减少比较次数
     * @param arr
     * @return
     */
    public int[] insertionBast(int[] arr){

        return arr;
    }


}
