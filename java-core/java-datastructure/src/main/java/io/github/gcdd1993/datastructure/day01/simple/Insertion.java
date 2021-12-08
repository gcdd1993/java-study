package io.github.gcdd1993.datastructure.day01.simple;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author gcdd1993
 * @date 2021/11/30
 * @since 1.0.0
 */
public class Insertion {

    /**
     * 对数组a中的元素进行排序
     */
    public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            // 当前元素为a[i],依次和i前面的元素比较，找到一个小于等于a[i]的元素
            for (int j = i; j > 0; j--) {
                // 虽然是插入，但是需要一个个交换
                if (greater(a[j - 1], a[j])) {
                    // 交换元素
                    exch(a, j - 1, j);
                } else {
                    //找到了该元素，结束
                    break;
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
        Insertion.sort(a);
        System.out.println(Arrays.toString(a));
    }

}
