package com;

import java.util.HashSet;
import java.util.Set;

public class Test {
    int a;
    int b;
    public Test(int a, int b){
        this.a = a;
        this.b = b;
    }
  
    public static void main(String[] args) {  
        Set<Long> set = new HashSet<Long>();
        set.add(new Long(1));
        set.add(new Long(1));
        Test t1 = new Test(1,1);
        Test t2 = new Test(2,2);
        System.out.println(t2.hashCode());
        Object a = null;
        System.out.println((Boolean)a);
    }  
  
}
