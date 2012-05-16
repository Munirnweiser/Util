package com.java.tech.concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class TestThreadLocal {
    static MyThreadLocal<String> local = new MyThreadLocal<String>();
    static{local.set("init");}
    
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(local.get());
                local.set("t1");
                System.out.println(local.get());
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(local.get());
                local.set("t2");
                System.out.println(local.get());
            }
        });
        t1.start();
        t2.start();
    }
    
    public static class MyThreadLocal<T>{
        Map<Thread,T> map = new ConcurrentHashMap<Thread, T>();
        public T get(){
            return map.get(Thread.currentThread());
        }
        public void set(T obj){
            map.put(Thread.currentThread(), obj);
        }
    }
}
