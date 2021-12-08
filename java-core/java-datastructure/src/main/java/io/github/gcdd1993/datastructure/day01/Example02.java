package io.github.gcdd1993.datastructure.day01;

/**
 * @author gcdd1993
 * @date 2021/11/30
 * @since 1.0.0
 */
public class Example02 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int sum = 0;
        int n = 100;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        System.out.println("sum=" + sum);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
