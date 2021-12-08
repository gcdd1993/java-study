package io.github.gcdd1993.datastructure.day01.advanced;

import java.util.Arrays;

/**
 * 快速排序
 * <p>
 * 切分操作递归可能会发生java.lang.StackOverflowError
 *
 * @author gcdd1993
 * @date 2021/11/30
 * @since 1.0.0
 */
public class Quick {

    /**
     * 对数组a中的元素进行排序
     */
    public static void sort(Comparable[] a) {
        int lo = 0;
        int hi = a.length - 1;
        sort(a, lo, hi);
    }

    /**
     * 对数组a中从索引lo到索引hi之间的元素进行排序
     */
    private static void sort(Comparable[] a, int lo, int hi) {
        // 安全性校验
        if (hi <= lo) {
            return;
        }

        // 对数组钟lo索引到hi索引处的元素进行分组（左子组和右子组）
        int partition = partition(a, lo, hi); // 返回的是分界值所在的索引，分界值变换后的索引

        // 让左子组有序
        sort(a, lo, partition - 1);
        // 让右子组有序
        sort(a, partition + 1, hi);
    }

    /**
     * 对数组a中，从索引 lo到索引 hi之间的元素进行分组，并返回分组界限对应的索引
     */
    public static int partition(Comparable[] a, int lo, int hi) {
        System.out.println("排序" + lo + ", " + hi);
        // 确定分界值
        Comparable key = a[lo];
        // 定义两个指针，分别指向待切分元素的最小索引处和最大索引处下一个位置
        int left = lo;
        int right = hi + 1;

        // 切分
        while (true) {
            // 先从右往左扫描，移动right指针，找到一个比分界值小的元素，停止
            while (less(key, a[--right])) { // 循环停止，证明找到了一个比基准值小的元素
                if (right == lo) {
                    break; // 已经扫描到最左边了，无需继续扫描
                }
            }
            // 从左往右扫描，移动left指针，找到一个比分界值大的元素，停止
            while (less(a[++left], key)) { // 循环停止，证明找到了一个比基准值大的元素
                if (left == hi) {
                    break; // 已经扫描到最右边了，无需继续扫描
                }
            }
            // 判断 left >= right，如果是，则证明元素扫描完毕，结束循环，如果不是，则交换元素
            if (left >= right) {
                break;
            } else {
                exch(a, left, right);
            }
        }

        // 交换最后right索引处和基准值所在的索引处的值
        exch(a, lo, right);
        return right; // right就是切分的界限
    }

    /**
     * 比较v元素是否小于w元素
     */
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
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
        Integer[] a = {9, 1, 2, 5, 7, 4, 8, 6, 3, 5};
        Quick.sort(a);
        System.out.println(Arrays.toString(a));
    }


}
