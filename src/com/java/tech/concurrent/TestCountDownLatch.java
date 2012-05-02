package com.java.tech.concurrent;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {
    private static final int THREAD_COUNT = 10;
    private static CountDownLatch ready = new CountDownLatch(1);
    private static CountDownLatch finish = new CountDownLatch(THREAD_COUNT);
    
    public static void main(String[] args) {
        System.out.println("Ready!!");
        for (int i = 1; i <= THREAD_COUNT; i++){
            final int j = i;
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Man_" + j + " prepared!");
                try {
                    ready.await();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("Man_" + j + " finished!");
                finish.countDown();
            }
        }).start();
        }
        try {
            Thread.sleep(1000);
            ready.countDown();
            System.out.println("Start!!");
            finish.await();
            System.out.println("All finished!!");
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}
