package com.algorithm.sort;


public class Sorts {
    public static void swap(int[] a, int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    public static int[] concat(int[] a, int[] b){
        int[] c = new int[a.length + b.length];
        int k = 0;
        for(int i : a){
            c[k++] = i;
        }
        for(int i : b){
            c[k++] = i;
        }
        return c;
    }
    public static void print(int[] a){
        for(int i : a){
            System.out.print(i+"\t");
        }
        System.out.println();
    }
    
    /**
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
    
    /**
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
    
    /**
     * 归并排序,将两个排序好的数组合并成一个完整的已排序的数组，
     * 分治法思想，一般通过递归将一个大数组拆分成小数组，小数组可采用其它排序方法如插入排序
     * 
     * 最差时间复杂度 O(nlog(n))
     * 最优时间复杂度 O(n)
     * 平均时间复杂度 O(nlog(n))
     * 最差空间复杂度 O(n)
     */
    public static int[] mergeSort(int[] a, int[] b){
        int[] c = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while(i < a.length && j < b.length){
            if (a[i] > b[j]){
                c[k++] = b[j++];
            } else{
                c[k++] = a[i++];
            }
        }
        while(j < b.length){
            c[k++] = b[j++];
        }
        while(i < a.length){
            c[k++] = a[i++];
        }
        return c;
    }
    
    /**
     * 快速排序,从数列中挑出一个数,将数列中大于这个数的数放到其右边，将数列中小于这个数的数放到其左边，
     * 分别选取左右两边来递归上述过程，直到最小粒度
     * 
     * 分治法思想
     * 
     * 最差时间复杂度 O(n^2)
     * 最优时间复杂度 O(nlog(n))
     * 平均时间复杂度 O(nlog(n))
     * 
     */
    public static int[] quickSort(int[] a, int begin, int end){
        if (begin >= end)return a;
        int index = (end + begin)/2;
        int pivot = a[index];
        //将选取的数换到end处，以备之后swap回来
        swap(a,index,end);
        //基数，仅仅是数值
        //index从新开始计算，到最后结束时index的值即为pivot所在的位置，即index左边都是小于pivot的，而右边则是大于pivot
        for (int i = index = begin; i < end; i++){
            //i的增长和index的增长不同步，index只有在碰到比pivot的小的数才增长
            //当a[i]>pivot时，index不变，也就是说index实际指示了第一个大于或等于pivot的数
            if (a[i] <= pivot){
                swap(a,index++,i);
            }
        }
        //当遍历完成,index仍指向第一个大于或等于pivot的数，将之与末尾的pivot做swap
        swap(a,index,end);//现在index上的数就是pivot,index左边的都小于等于它，右边的都大于等于它
        print(a);
        quickSort(a,begin,index - 1);
        quickSort(a,index+1,end);
        return a;
    }
    
    /**
     * 位图排序
     * 
     * 原理：如果以某一bit位置1或0来判断对应数字的存在，则一个int数可表示32个数，long类似
     * 
     * 适合大量数据，但有限制条件，即不能重复、数的范围需可知
     * JAVA已有相关实现：BitSet
     */
    public static class BitMapSort{
        private final int SHIFT = 5; //0~31需5位，将数字右移5位会导致有32个数指向同一个数
        private final int MASK = 0x1f;//11111, 和它做&来屏蔽更高位,相当于模操作
        private int[] array = new int[1250000];//存储40亿个数
        
        public void set(int i){
            array[i >> SHIFT] |= 1 << (i & MASK);
        }
        
        public void clr(int i){
            array[i >> SHIFT] &= ~(1 << (i & MASK));
        }
        
        //if return 0, 说明不存在，否则返回(1 << (i & MASK))
        public int test(int i){
            return array[i >> SHIFT] & (1 << (i & MASK));
        }
        
        public int[] sort(int[] a){
            int[] b = new int[a.length];
            for (int i : a){
                set(i);
            }
            int j = 0;
            for (int i = 0; i < 10000000; i++){
                if(test(i) > 0){
                    b[j++] = i;
                }
            }
            return b;
        }
    }
    
    public static void main(String[] args) {
        int[] a = new int[]{14,1,5,2,3,11,4};
        //bubbleSort(a);
        //insertSort(a);
        //print(a);
        //BitMapSort b = new BitMapSort();
        //a = b.sort(a);
        quickSort(a,0,a.length-1);
        print(a);
        
    }
}
