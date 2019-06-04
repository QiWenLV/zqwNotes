package com.sort;

import com.utils.RandomArray;
import org.junit.Test;

/**
 * 选择排序
 */
public class SelectionSort {

    @Test
    public void test1(){
        int[] array = RandomArray.getArray(10);
        RandomArray.printArray("原始数组", array);

        RandomArray.printArray("选择排序", selectionSimple(array));
    }

    @Test
    public void test2(){
        int[] array = RandomArray.getArray(10);
        RandomArray.printArray("原始数组", array);

        RandomArray.printArray("选择排序", selectionBast(array));
    }


    public int[] selectionSimple(int[] arr){

        //记录当前趟数的最大值的角标
        int pos ;
        //交换的变量
        int temp;

        //外层循环控制需要排序的趟数
        for (int i = 0; i < arr.length - 1; i++) {
            //新的趟数、将角标重新赋值为0
            pos = 0;
            //内层循环控制遍历数组的个数并得到最大数的角标
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[pos]) {
                    pos = j;
                }
            }
            //交换
            temp = arr[pos];
            arr[pos] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
        return arr;
    }


    /**
     * 优化选择排序法
     * 每次循环都从数组中选出最大值和最小值，并分别与左右两边的位置交换。
     * @param arr
     * @return
     */
    public int[] selectionBast(int[] arr){

        int left = 0, right = arr.length - 1;

        //交换的变量
        int temp;

        while (left < right){
            int max = right, min=left;

            for (int i = left; i <= right; i++) {
                if(arr[i] > arr[max]){
                    max = i;
                }
                if(arr[i] < arr[min]){
                    min = i;
                }
            }
            //将最大值交换到右边
            temp = arr[max];
            arr[max] = arr[right];
            arr[right] = temp;

            //当最小值坐标就是最右边的坐标，在上面的arr[right]与arr[max]的值交换后，arr[left]就应该与arr[max]交换
            if (min == right){
                min = max;
            }

            //交换最小值到左边
            temp = arr[min];
            arr[min] = arr[left];
            arr[left] = temp;

            //缩小范围
            left++;
            right--;
        }

        return arr;
    }
}
