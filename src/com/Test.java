package com;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Test implements Serializable{
    private String str;
    public String getStr() {
        return str;
    }
    public void setStr(String str) {
        this.str = str;
    }
    private void readObject(java.io.ObjectInputStream in){
        System.out.println("test");
    }
    public void test(){
        System.out.println("test");
    }
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        char c = 'a';
        int num = 0;
        for (int i = 0; i < 16; i++){
            if ((c & (1 << i)) == 1 << i){
                num++;
            }
        }
        System.out.println(num);
    }
}
class T extends Test{
    @Override
    public void test() {
        System.out.println("TT");
    }
    public double test(String s){
        System.out.println("String");
        return 1d;
    }
    public void test(int[] s){
        System.out.println("int[]");
    }
    public <E> E t(List<? extends E> l, E e){
        return e;
    }
}
