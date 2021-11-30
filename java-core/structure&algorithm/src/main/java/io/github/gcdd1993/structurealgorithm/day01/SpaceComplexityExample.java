package io.github.gcdd1993.structurealgorithm.day01;

/**
 * 空间复杂度例子
 *
 * @author gcdd1993
 * @date 2021/11/30
 * @since 1.0.0
 */
public class SpaceComplexityExample {

    /**
     * 对指定的数组元素进行反转，并返回反转的内容。
     */
    public static class Ex01 {

        /**
         * 解法一
         */
        public static class Solution01 {
            public static int[] reverse1(int[] arr) {
                int n = arr.length;//申请4个字节
                int temp;//申请4个字节
                for (int start = 0, end = n - 1; start <= end; start++, end--) {
                    temp = arr[start];
                    arr[start] = arr[end];
                    arr[end] = temp;
                }
                return arr;
            }
        }

        /**
         * 解法二
         */
        public static class Solution02 {
            public static int[] reverse2(int[] arr) {
                int n = arr.length;//申请4个字节
                int[] temp = new int[n];//申请n*4个字节+数组自身头信息开销24个字节
                for (int i = n - 1; i >= 0; i--) {
                    temp[n - 1 - i] = arr[i];
                }
                return temp;
            }
        }
    }

}
