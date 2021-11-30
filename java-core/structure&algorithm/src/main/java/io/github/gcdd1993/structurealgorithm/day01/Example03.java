package io.github.gcdd1993.structurealgorithm.day01;

/**
 * @author gcdd1993
 * @date 2021/11/30
 * @since 1.0.0
 */
public class Example03 {

    /**
     * 计算100个1+100个2+100个3+...100个100的结果
     */
    public static void main(String[] args) {
        int sum = 0;
        int n = 100;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sum += i;
            }
        }
        System.out.println("sum=" + sum);
    }
}
