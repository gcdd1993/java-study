package io.github.gcdd1993.datastructure.day01;

/**
 * 计算1到100的和
 *
 * @author gcdd1993
 * @date 2021/11/24
 * @since 1.0.0
 */
public class Example01 {

    public static class Solution01 {
        public static void main(String[] args) {
            int sum = 0; // 执行1次
            int n = 100; // 执行1次
            for (int i = 1; i <= n; i++) { // 执行了n+1次
                sum += i; // 执行了n次
            }
            System.out.println("sum=" + sum);
        }
    }

    public static class Solution02 {
        public static void main(String[] args) {
            int sum = 0; // 执行1次
            int n = 100; // 执行1次
            sum = (n + 1) * n / 2; // 执行1次
            System.out.println("sum=" + sum);
        }
    }

    public static class Solution03 {
        public static void main(String[] args) {
            int sum = 0;//执行1次
            int n = 100;//执行1次
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    sum += i;//执行n^2次
                }
            }
            System.out.println("sum=" + sum);
        }
    }
}
