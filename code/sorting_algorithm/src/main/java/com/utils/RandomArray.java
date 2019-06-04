package com.utils;

import java.util.Random;

public class RandomArray {


    public static int[] getArray(int capacity){

        int[] rarr = new int[capacity];

        for (int i = 0; i < rarr.length; i++) {
            rarr[i] = (int) (Math.random() * 100);
        }

        return rarr;
    }


    public static void printArray(int[] arr){
        for (int anArr : arr) {
            System.out.print(anArr + "  ");
        }
        System.out.println();
    }

    public static void printArray(String signInfo, int[] arr){
        System.out.print(signInfo + ": ");
        for (int anArr : arr) {
            System.out.print(anArr + "  ");
        }
        System.out.println();
    }
}
