package com.zqw.test.test_thread;

/**
 * 测试volatile关键字
 */
public class Test_Volatile extends Thread{

    private volatile boolean stop = false;
//    private  boolean stop = false;

    public void stopMe(){
        stop = true;
    }

    @Override
    public void run() {
        int i = 0;
        //监听stop变量的改变
        while (!stop) {
            try {
                i++;
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Stop thread  " + i);
    }

    public static void main(String[] args) throws InterruptedException {
        Test_Volatile t = new Test_Volatile();

        t.start();
        Thread.sleep(1000);
        t.stopMe();
        Thread.sleep(1000);
    }
}
