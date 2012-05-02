package com.algorithm.common;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Questions {
    static int[] a ;
    static{a[0]=1;}
    static int[] b;
    
    public static void main(String[] args) {
    }
    /**
     * 题目：不使用"+","-","*","/"来实现加法
     * 
     * 原理：a+b = (b==0?a:(a^b + (a&b)<<1))
     * 最多需要加数二进制位长度次迭代，进位补偿就变为0
     */
    public static int addWithoutArithmetic(int d1,int d2){
        if(d2 == 0)return d1;
        //无进位值
        int a = d1 ^ d2;
        //进位值
        int b = (d1 & d2) << 1;
        //递归，直到不产生新的进位
        System.out.println("a:" + a);
        System.out.println("b:" + b);
        return addWithoutArithmetic(a , b);
    }
    
    /**
     * 题目：求1+2+…+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字
     * 以及条件判断语句（A?B:C）。
     * 
     * 原理：递归等价于循环
     */
    public static int summationOfSeries(int num, int step, int ret){
        //初始化，决定递归次数step
        if (step == -1)step = num - 1;
        if (ret == 0)ret = num;
        //step为0时计算结束
        if (step == 0)return ret;
        step -= 1;
        //每次做一个加法
        ret += num - 1;
        return summationOfSeries(num - 1, step, ret);
    }
    
    /**
     * 题目：在数组中，数字减去它右边的数字得到一个数对之差。求所有数对之差的最大值。
     * 例如在数组{2, 4, 1, 16, 7, 5, 11, 9}中，数对之差的最大值是11，是16减去5的结果。
     * 
     * 原理：(数列：找An与An+1的关系)设数组a，如果已知第一个数到 a.length - 1个数的maxDiff diff1，那最终的maxDiff
     * 应为diff1或a.length-1个数的最大值减去尾数。
     */
    public static int findMaxDiff(int a[]){
        if (a == null || a.length < 2) {
            return 0;
        }
        int max = a[0];
        int maxDiff = a[0] - a[1];
        for (int i = 2; i < a.length; i++){
            if (a[i - 1] > max){
                max = a[i - 1];
            }
            int currDiff = max - a[i];
            if (currDiff > maxDiff){
                maxDiff = currDiff;
            } 
        }
        return maxDiff;
    }
    
    /**
     * 题目：斐波那契数列，即{0，1，1，2，3，5，8，13....}
     * 原理：动态规划
     */
    static BigInteger fibonacciPolynomial(int n, List<BigInteger> list){
        if(list == null){
            list = new ArrayList<BigInteger>();
            list.add(0, new BigInteger("0"));
            list.add(1, new BigInteger("1"));
        }
        if (list.size() < n ){
            list.add(n - 1, fibonacciPolynomial(n-2,list).add(fibonacciPolynomial(n-1,list)));
        }
        return list.get(n - 1);
    }
    
    /**
     *  题目：输入一个字符串，打印出该字符串中字符的所有排列。
     *  例如输入字符串abc，则输出由字符a、b、c所能排列出来的所有字符串abc、acb、bac、bca、cab和cba。
     *  原理：动态规划
     */
    static List<String> charPermutation(String str, List<String> list){
        if (list == null) list = new ArrayList<String>();
        if (str != null && str.length() > 0){
            if (str.length() == 1){
                list.add(new String(str));
            } else {
                String temp1 = str.substring(0, str.length() - 1);
                String temp2 = str.substring(str.length() - 1);
                //抠去最后一个字符，递归找之前的排列
                list =  charPermutation(temp1, list);
                if (!list.get(0).contains(temp2)){
                    List<String> tempList = new ArrayList<String>();
                    Iterator<String> it = list.iterator();
                    while(it.hasNext()){
                        String s = it.next();
                        //把字符插进字符串的各个point形成新的排列
                        for (int i = 0; i <= s.length(); i++){
                            String s1 = s.substring(0,i);
                            String s2 = s.substring(i,s.length());
                            tempList.add(s1 + temp2 + s2);
                        }
                    }
                    list = tempList;
                }
            }
        } 
        return list;
    }
    
    
    /**
     *  题目：输入一个字符串，输出该字符串中字符的所有组合。
     *  举个例子，如果输入abc，它的组合有a、b、c、ab、ac、bc、abc。
     *  原理：动态规划
     */
    static List<String> charCombination(String str, List<String> list){
        if (list == null) list = new ArrayList<String>();
        if (str != null && str.trim().length() > 0){
            if (str.length() == 1){
                list.add(str);
            } else {
                String temp1 = str.substring(0, str.length() - 1);
                String temp2 = str.substring(str.length() - 1);
                list =  charCombination(temp1, list);
                if (!list.contains(temp2)){
                    List<String> tempList = new ArrayList<String>();
                    tempList.add(temp2);
                    Iterator<String> it = list.iterator();
                    //形成新的组合
                    while(it.hasNext()){
                        String s = it.next() + temp2;
                        tempList.add(s);
                    }
                    list.addAll(tempList);
                }
            }
        } 
        return list;
    }
    
    /**
     * 题目：整数背包问题， 设有i件物品，每件价值记为Pi，每件体积记为Vi，
     * 用一个最大容积为Vmax的背包，求装入物品的最大价值,每件物品最多放1件。
     * 原理：动态规划。
     * 分析：如果n-1个物品放在体积为maxV的背包最大价值为f(n-1,maxV),则n个物品有两种情况：
             1）第n个物品不放进背包，最大价值仍为f(n-1,maxV)
             2）第n个物品放进背包，最大价值为f(n-1,maxV-Vn)+Pn
                                  两者最大值即为背包最大价值
     */
    static int knapsackV(int[] p,int[] v,int maxV){
        if (maxV <= 0 || p == null || v == null || p.length != v.length){
            return 0;
        }
        int size = p.length;
        if (size == 1){
            if (v[0] <= maxV){
                return p[0];
            } else {
                return 0;
            }
        }
        //不能把原数组传进去，以免在执行第一个递归时改变了数组的值，
        //然后再去执行第二个递归时参数非预期的改变
        int[] tempArryP = new int[size - 1];
        int[] tempArryV = new int[size - 1];
        System.arraycopy(p, 0, tempArryP, 0, size - 1);
        System.arraycopy(v, 0, tempArryV, 0, size - 1);
        int tempP = p[size - 1];
        int tempV = v[size - 1];
        
        if (tempV > maxV)return knapsackV(tempArryP,tempArryV,maxV);
        
        int preMaxP = knapsackV(tempArryP,tempArryV,maxV);
        int currMaxP = knapsackV(tempArryP,tempArryV,maxV - tempV) + tempP;
        if (preMaxP >= currMaxP) {
            return preMaxP;
        } else {
            return currMaxP;
        }
    }
    
    /**
     * 题目：在8×8的国际象棋上摆放八个皇后，使其不能相互攻击，
     * 即任意两个皇后不得处在同一行、同一列或者同一对角斜线上。求有多少种摆法。
     * 原理：动态规划
     */
    static int queen_main(int n){
        if (n < 1)return 0;
        int num = 0;
        //建立一个数组表示每行queen所在列号（queen不同行也不同列）
        int[] a = new int[n];
        for (int i = 0; i < n; i++){
            a[i] = i;
        }
        List<int[]> list = queen_permutation(a,null);
        for (int[] in : list){
            if (queen_check(in)) num++;
        }
        
        return num;
    }
    //检查是否有两个queen在对角线上
    static boolean queen_check(int[] a){
        for (int i = 0; i < a.length; i++){
            for (int j = a.length - 1; j > i; j--){
                if (Math.abs(a[j] - a[i]) == Math.abs(j - i))return false;
            }
        }
        return true;
    }
    //得到所有排列，类似于字符串的排列
    static List<int[]> queen_permutation(int[] a , List<int[]> list){
        if (list == null)list = new ArrayList<int[]>();
        if (a.length > 0){
            if (a.length == 1){
                list.add(a);
            } else {
                int[] temp = new int[a.length - 1];
                System.arraycopy(a, 0, temp, 0, a.length - 1);
                int t = a[a.length - 1];
                List<int[]> tempList = new ArrayList<int[]>();
                list = queen_permutation(temp,list);
                for (int[] i : list){
                    for (int j = 0; j <= i.length; j++){
                        int[] temp2 = new int[i.length + 1];
                        temp2[j] = t;
                        System.arraycopy(i, 0, temp2, 0, j);
                        System.arraycopy(i, j, temp2, j + 1, i.length - j);
                        tempList.add(temp2);
                    }
                }
                list = tempList;
            }
        }
        return list;
    }
    
    /**
     * 
     * 题目：输入一个整形数组，数组里有正数也有负数。数组中连续的一个或多个整数组成一个子数组，
     * 每个子数组都有一个和。求所有子数组的和的最大值。要求时间复杂度为O(n)。
     * 例如输入的数组为1, -2, 3, 10, -4, 7, 2, -5，和最大的子数组为3, 10, -4, 7, 2，因此输出为该子数组的和18。
     *
     */
    public static int maxSubArray(int[] a){
        if (a == null || a.length <0)return 0;
        int sum = 0;
        int max = 0;
        int num = 0;
        for (int i = 0; i < a.length; i++){
            sum += a[i];
            if (sum < 0) sum = 0;
            if (sum > max) max = sum;
            num++;
        }
            System.out.println(num);
            if (max == 0){
                int maxNum = a[0];
                for (int j : a){
                    if (j > maxNum){
                        maxNum = j;
                    }
                }
                return maxNum;
            }
            return max;
    }
    
    /**
     * 
     * 题目：已有40亿个未排序不复数的int数，现提供一个新数，判断是否这40亿个数之一
     * 原理：位图排序
     */
    
    //main 方法可以写在static内部类里
    static class A{
        public static void main(String[] args) {
            int[] a = new int[]{-1, -2, -3, -10, -4, -7, -2, -5};
            System.out.println(maxSubArray(a));
        }
    }
}
