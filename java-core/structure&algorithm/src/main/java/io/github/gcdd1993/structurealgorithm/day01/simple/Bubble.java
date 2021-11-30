package io.github.gcdd1993.structurealgorithm.day01.simple;

import java.util.Arrays;

/**
 * 冒泡排序 O(n^2)
 *
 * @author gcdd1993
 * @date 2021/11/30
 * @since 1.0.0
 */
public class Bubble {

    /**
     * 对数组a中的元素进行排序
     */
    public static void sort(Comparable[] a) {
        for (int i = a.length - 1; i > 0; i--) { // 倒序遍历
            for (int j = 0; j < i; j++) {
                if (greater(a[j], a[j + 1])) {
                    exch(a, j, j + 1);
                }
            }
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
        Bubble.sort(a);
        System.out.println(Arrays.toString(a));
    }

}
