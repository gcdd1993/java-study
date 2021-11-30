package io.github.gcdd1993.structurealgorithm.day01.advanced;

/**
 * @author gcdd1993
 * @date 2021/11/30
 * @since 1.0.0
 */
public class RecursionTest {

    public static void main(String[] args) throws Exception {
        int result = factorial(5);
        System.out.println(result);
    }

    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

}
