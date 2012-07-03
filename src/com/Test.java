package com;

import java.lang.ref.WeakReference;


public  class Test {
    int a;
    int b;
    static String s ;
    public Test(int a, int b){
        this.a = a;
        this.b = b;
    }
    //一个排好序的数组，找出两数之和为m的所有组合
    public static void get(int[] a, int m){
    	int temp = 0;
    	int count = 0;
    	for (int i = 0; i < a.length; i++){
    		if (a[i]*2 < m){
    			for (int j = i + 1; j < a.length; j ++){
    				if (a[j]*2 > m){
    					count++;
    					temp = a[i] + a[j];
    					if (temp == m){
    						System.out.println(a[i] + ":" + a[j]);
    					} else if(temp > m){
    						break;
    					}
    				}
    			}
    		}
    	}
    	System.out.println("count:"+count);
    }
    //3、自然数序列，找出任意连续之和等于n的所有子序列
    public static void get1(int[] a, int n){
    	int temp = 0;
    	StringBuilder sb = null;
    	for (int i = 0; i < a.length; i++){
    		temp = a[i];
    		sb = new StringBuilder();
    		sb.append(a[i]);
    		if (temp == n){
    			System.out.println(sb);
    		} else if (temp < n){
    			for (int j = i + 1; j < a.length; j++){
    				temp += a[j];
    				if (temp == n){
    					sb.append(":"+a[j]);
    	    			System.out.println(sb);
    	    		} else if (temp > n){
    	    			break;
    	    		}
    			}
    		}
    	}
    }
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {  
    	int[] a = { 1, 2, 3, 0,4, 5,0 };
    	get1(a,5);
    }  
  
}
