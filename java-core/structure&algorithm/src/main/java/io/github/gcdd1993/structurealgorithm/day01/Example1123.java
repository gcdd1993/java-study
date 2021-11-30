package io.github.gcdd1993.structurealgorithm.day01;

/**
 * @author gcdd1993
 * @date 2021/11/30
 * @since 1.0.0
 */
public class Example1123 {

    /**
     * O(n)
     */
    public static class Ex01 {
        public static void main(String[] args) {
            int n = 100;
            for (int i = 0; i < n; i++) { // O(n)
                show(i);
            }
        }

        private static void show(int i) {
            System.out.println(i); // O(1)
        }
    }

    public static class Ex02 {
        public static void main(String[] args) {
            int n = 100;
            for (int i = 0; i < n; i++) {  // O(n)
                show(i);
            }
        }

        private static void show(int i) {
            for (int j = 0; j < i; i++) { // O(n)
                System.out.println(i);
            }
        }
    }

    public static class Ex03 {
        public static void main(String[] args) {
            int n = 100;
            show(n);
            for (int i = 0; i < n; i++) { // O(n)
                show(i);
            }
            for (int i = 0; i < n; i++) {  // O(n)
                for (int j = 0; j < n; j++) {  // O(n)
                    System.out.println(j);
                }
            }
        }

        private static void show(int i) {
            for (int j = 0; j < i; i++) {  // O(n)
                System.out.println(i);
            }
        }
    }

    public static class Ex04 {
        public int search(int num) {
            int[] arr = {11, 10, 8, 9, 7, 22, 23, 0};
            for (int i = 0; i < arr.length; i++) {
                if (num == arr[i]) {
                    return i;
                }
            }
            return -1;
        }
    }

}
