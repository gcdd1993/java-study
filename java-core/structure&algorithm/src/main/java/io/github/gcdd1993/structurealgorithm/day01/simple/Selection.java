package io.github.gcdd1993.structurealgorithm.day01.simple;

import java.util.Arrays;

/**
 * 选择排序 O(n^2)
 *
 * @author gcdd1993
 * @date 2021/11/30
 * @since 1.0.0
 */
public class Selection {

    /**
     * 对数组a中的元素进行排序
     */
    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length - 2; i++) { // 为什么是 a.length - 2
            //假定本次遍历，最小值所在的索引是i
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) { // (N-1)+(N-2)+(N-3)+...+2+1=((N-1)+1)*(N-1)/2=N^2/2-N/2
                if (greater(a[minIndex], a[j])) {
                    //跟换最小值所在的索引
                    minIndex = j;
                }
            }
            //交换i索引处和minIndex索引处的值
            exch(a, i, minIndex); // N-1
        }
    }

    /**
     * 比较v元素是否大于w元素
     */
    private static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    /**
     * 数组元素i和j交换位置
     */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] a = {4, 5, 6, 3, 2, 1};
        Selection.sort(a);
        System.out.println(Arrays.toString(a));
    }

}
