package com.algorithm.sort;

public class Sorts {
    public static void swap(int[] a, int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    public static void print(int[] a){
        for(int i : a){
            System.out.print(i+"\t");
        }
        System.out.println();
    }
    
    /*
     * 冒泡排序
     * 平均情况：O（n^2）
     * 最好情况：O（n^2）
     * 最坏情况：O（n^2）
     */
    public static int[] bubbleSort(int[] a){
        if (a == null || a.length == 0)return null;
        for (int i = 0; i < a.length; i++){
            for (int j = a.length - 1; j > i; j--){
                if (a[j - 1] > a[j]){
                    swap(a, j-1, j);
                }
            }
        }
        return a;
    }
    
    /*
     * 插入排序,每次插入第K+1到前K个数中的合适位置（从后比较，每次都swap,直到位置合适）
     * 平均情况：O（n^2）
     * 最好情况：O（n）
     * 最坏情况：O（n^2）
     * 
     * NOTE：量级小时，插入排序是一个不错的选择。Arrays.sort方法中，length小于7时就使用
     * 插入排序，>=7时使用merge sort(归并)
     */
    public static int[] insertSort(int[] a){
        if (a == null || a.length == 0)return null;
        if (a.length == 1)return a;
        for (int i = 1; i < a.length; i++){
            for (int j = i - 1; j >= 0; j--){
                if(a[j] > a[j + 1]){
                    swap(a, j, j + 1 );
                    print(a);
                }
            }
        }
        return a;
    }
    
    /*
     * 归并排序
     */
    public static int[] mergeSort(int[] a){
        return a;
    }
    
    
    public static void main(String[] args) {
        int[] a = new int[]{14,1,-1,2,3,11,4};
        //bubbleSort(a);
        insertSort(a);
        print(a);
    }
}
