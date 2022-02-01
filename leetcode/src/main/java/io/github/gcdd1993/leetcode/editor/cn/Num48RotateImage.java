package io.github.gcdd1993.leetcode.editor.cn;

public class Num48RotateImage {
    public static void main(String[] args) {
        Solution solution = new Num48RotateImage().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 顺时针旋转矩阵
         * <p>
         * 1、按照左上到右下的对角线做镜像对称
         * 2、反转矩阵的每一行
         *
         * @param matrix
         */
        public void rotate(int[][] matrix) {
            // 按照左上到右下的对角线做镜像对称
            reverse(matrix);
            // 反转矩阵的每一行
            for (int[] row : matrix) {
                reverse(row);
            }

        }

        /**
         * 按照左上到右下的对角线做镜像对称
         *
         * @param matrix
         */
        private void reverse(int[][] matrix) {
            for (int i = 0; i < matrix.length; i++) {
                int[] row = matrix[i];
                // 注意，这里 j 的起始位置是 i
                for (int j = i; j < row.length; j++) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }

        /**
         * 反转数组
         *
         * @param arr
         */
        private void reverse(int[] arr) {
            int i = 0, j = arr.length - 1;
            while (j > i) {
                // swap(arr[i], arr[j]);
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}