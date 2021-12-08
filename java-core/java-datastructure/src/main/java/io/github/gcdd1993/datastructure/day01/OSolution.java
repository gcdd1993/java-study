package io.github.gcdd1993.datastructure.day01;

/**
 * @author gcdd1993
 * @date 2021/11/30
 * @since 1.0.0
 */
public class OSolution {

    /**
     * 线性阶
     * 一般含有非嵌套循环涉及线性阶，线性阶就是随着输入规模的扩大，对应计算次数呈直线增长
     */
    public static class Ex01 {
        public static void main(String[] args) {
            int sum = 0;
            int n = 100;
            for (int i = 1; i <= n; i++) {
                sum += i;
            }
            System.out.println("sum=" + sum);
        }
    }

    /**
     * 平方阶
     * 一般嵌套循环属于这种时间复杂度
     */
    public static class Ex02 {
        public static void main(String[] args) {
            int sum = 0, n = 100;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    sum += i;
                }
            }
            System.out.println(sum);
        }
    }

    /**
     * 立方阶
     * 一般三层嵌套循环属于这种时间复杂度
     */
    public static class Ex03 {
        public static void main(String[] args) {
            int x = 0, n = 100;
            for (int i = 1; i <= n; i++) {
                for (int j = i; j <= n; j++) {
                    for (int k = i; k <= n; k++) {
                        x++;
                    }
                }
            }
            System.out.println(x);
        }
    }

    /**
     * 对数阶
     */
    public static class Ex04 {
        public static void main(String[] args) {
            int i = 1, n = 100;
            while (i < n) {
                i = i * 2;
            }
        }
    }

    /**
     * 常数阶
     * 一般不涉及循环操作的都是常数阶，因为它不会随着n的增长而增加操作次数。
     */
    public static class Ex05 {
        public static void main(String[] args) {
            int n = 100;
            int i = n + 2;
            System.out.println(i);
        }
    }

}
