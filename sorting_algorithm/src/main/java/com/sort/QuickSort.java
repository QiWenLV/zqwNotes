package com.sort;

import com.utils.RandomArray;
import org.junit.Test;

/**
 * 快速排序
 */
public class QuickSort {


    @Test
    public void test1(){
        int[] array = RandomArray.getArray(10);
        RandomArray.printArray("原始数组", array);

        RandomArray.printArray("快速排序", quickSimple(array, 0, array.length - 1));

    }

    @Test
    public void test2(){
        int[] array = RandomArray.getArray(30);
        RandomArray.printArray("原始数组", array);

        RandomArray.printArray("快速排序优化", quickAndInsertion(array, 0, array.length - 1));
    }

    /**
     * 简单实现快速排序
     * @param arr       //待排数组
     * @param left      //第一个下标
     * @param right     //最后一个下标
     * @return
     */
    public int[] quickSimple(int[] arr, int left, int right){

        int i = left;
        int j = right;

        //计算支点
        int pivot = arr[(left + right) /2];     //取中间值为支点

        while (i <= j){
            //寻找比支点小的数
            while (pivot > arr[i]){
                i++;
            }

            //寻找比支点大的数
            while (pivot < arr[j]){
                j--;
            }

            //将两个数交换位置
            if(i <= j){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        //上面一个while保证了第一趟排序支点的左边比支点小，支点的右边比支点大了。

        //递归
        if(left < j){
            quickSimple(arr, left, j);
        }
        if(i < right){
            quickSimple(arr, i, right);
        }
        return arr;
    }


    /**
     * 插入排序最大数组长度值
     */
    private static final int MAX_LENGTH_INSERT_SORT = 7;

    /**
     * 快速排序优化之一：
     * 使用三值取中确定支点
     * 当分割子集足够小的时候，可以选择插入排序，效率更高
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public int[] quickAndInsertion(int[] arr, int left, int right){
        int i = left;
        int j = right;

        //记录支点的值
        int pivot;

        //判断是继续分支，还是使用插入排序
        if((right - left + 1) > MAX_LENGTH_INSERT_SORT){
            //快速排序
            //使用三值取中来确定支点
            int mid = left + ((right - left) >> 1); // mid = low + (high-low)/2, 中间元素下标
            // 使用三值取中得到枢轴
            if (arr[left] > arr[right]) { // 目的：让arr[left] <= arr[right]
                swap(arr, left, right);
            }
            if (arr[mid] > arr[right]) { // 目的：让arr[mid] <= arr[right]
                swap(arr, mid, right);
            }
            if (arr[mid] > arr[left]) { // 目的： 让arr[left] >= arr[mid]
                swap(arr, left, mid);
            }
            // 经过上述变化，最终 arr[mid]<=arr[left]<=arr[right]，则arr[left]为中间值
            pivot = arr[left];

            //确定支点之后，开始扫描
            while (left < right){

                while (left < right && arr[right] >= pivot){
                    right --;
                }
                //一旦扫描到小于支点是数，则替换
                arr[left] = arr[right];

                while (left < right && arr[left] <= pivot){
                    left ++;
                }
                //一旦扫描到大于支点的数，则替换
                arr[right] = arr[left];

            }
            //扫描结束(还原被替换的值)
            arr[left] = pivot;

            //此时left和right是重合的
            quickAndInsertion(arr, i, left -1);
            quickAndInsertion(arr, left + 1, j);

        }else {
            //插入排序
            int tmp;
            int y;
            // 从下标left+1开始遍历,因为下标为left的已经排好序
            for (int x = left + 1; x <= right; x++) {
                //如果当前值比前一个值还小，则需要进行插入
                if (arr[x - 1] > arr[x]) {
                    tmp = arr[x]; // 记录下标i对应的元素
                    //寻找插入位置
                    for (y = x - 1; y >= left && arr[y] > tmp; y--) {
                        arr[y + 1] = arr[y]; // 记录后移
                    }
                    arr[y + 1] = tmp; // 插入正确位置
                }
            }
        }

        return arr;
    }

    /**
     * 交换数组arr中的第i个位置和第j个位置的关键字
     */
    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
