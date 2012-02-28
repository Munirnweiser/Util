package com.java.tech;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements MyProxyIf{

    @Override
    public void doSomething() {
        System.out.println("Doing something....");
    }
    
    public static void main(String[] args) {
        MyProxyHandler handler = new MyProxyHandler();
        handler.setTestProxy(new DynamicProxy());
        MyProxyIf p = (MyProxyIf) Proxy.newProxyInstance(DynamicProxy.class.getClassLoader(), new Class[]{MyProxyIf.class}, handler);
        p.doSomething();
        System.out.println(p.getClass().getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader());
    }
}

interface MyProxyIf {
    void doSomething();
}

class MyProxyHandler implements InvocationHandler{
    private DynamicProxy testProxy;
    public void setTestProxy(DynamicProxy testProxy){
        this.testProxy = testProxy;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (testProxy != null){
            System.out.println("Before proxy call...");
            Object result = method.invoke(testProxy, args);
            System.out.println("After proxy call...");
            return result;
        }
        System.out.println("Error...no TestProxy object.");
        return null;
    }}