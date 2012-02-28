package com.java.tech;

public class TestClassLoader extends ClassLoader{
    public static void main(String[] args) throws ClassNotFoundException {
        Class c1 = ClassLoader.getSystemClassLoader().loadClass("java.lang.String");
        Class c11 = new TestClassLoader().loadClass("com.test.TestClassLoader");
        Class c2 = ClassLoader.getSystemClassLoader().loadClass("com.test.TestClassLoader");
        System.out.println(c2.equals(c11));
        System.out.println(c2.getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader().getResource(""));
        System.out.println(c2.getClassLoader().getParent());
    }
}
