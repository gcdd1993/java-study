package io.github.gcdd1993.leetcode.editor.cn;

public class Num304RangeSumQuery2dImmutable {
    public static void main(String[] args) {
        NumMatrix solution = new Num304RangeSumQuery2dImmutable().new NumMatrix(
                new int[][]{
                        new int[]{3, 0, 1, 4, 2},
                        new int[]{5, 6, 3, 2, 1},
                        new int[]{1, 2, 0, 1, 5},
                        new int[]{4, 1, 0, 1, 7},
                        new int[]{1, 0, 3, 0, 5}
                }
        );
        System.out.println("[2,1,4,3]: " + solution.sumRegion(2, 1, 4, 3));
        System.out.println("[1,1,2,2]: " + solution.sumRegion(1, 1, 2, 2));
        System.out.println("[1,2,2,4]: " + solution.sumRegion(1, 2, 2, 4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class NumMatrix {

        // preSum[i][j] 记录矩阵 [0, 0, i, j] 的元素和
        private int[][] preSum;

        /**
         * 矩阵的前缀和
         *
         * <img src="https://cdn.jsdelivr.net/gh/gcdd1993/image-repo@master/img/202201310036317.png"/>
         *
         * @param matrix
         */
        public NumMatrix(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;

            if (n == 0) {
                return;
            }

            // 构造前缀和矩阵
            preSum = new int[m + 1][n + 1];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 计算每个矩阵 [0, 0, i, j] 的元素和
                    preSum[i + 1][j + 1] = preSum[i][j + 1] + preSum[i + 1][j] + matrix[i][j] - preSum[i][j];
                }
            }

        }

        /**
         * 计算子矩阵 [row1, col1, row2, col2] 的元素和
         */
        public int sumRegion(int row1, int col1, int row2, int col2) {
            // fixme 为什么 row2和col2需要+1？
            return preSum[row2 + 1][col2 + 1] + preSum[row1][col1] - preSum[row1][col2 + 1] - preSum[row2 + 1][col1];
        }
    }

    /**
     * Your NumMatrix object will be instantiated and called as such:
     * NumMatrix obj = new NumMatrix(matrix);
     * int param_1 = obj.sumRegion(row1,col1,row2,col2);
     */
    //leetcode submit region end(Prohibit modification and deletion)

}