package io.github.gcdd1993.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class Num54SpiralMatrix {
    public static void main(String[] args) {
        Solution solution = new Num54SpiralMatrix().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * <img src="https://cdn.jsdelivr.net/gh/gcdd1993/image-repo@master/img/202201311512930.png"/>
         *
         * @param matrix
         * @return
         */
        public List<Integer> spiralOrder(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            int upperBound = 0;
            int rightBound = n - 1;
            int leftBound = 0;
            int lowerBound = m - 1;

            List<Integer> res = new ArrayList<>(m * n);
            while (res.size() < m * n) {
                if (upperBound <= lowerBound) {
                    // 在顶部从左向右遍历
                    for (int i = leftBound; i <= rightBound; i++) {
                        res.add(matrix[upperBound][i]);
                    }
                    // 上边界下移
                    upperBound++;
                }
                if (leftBound <= rightBound) {
                    // 在右侧从上往下遍历
                    for (int i = upperBound; i <= lowerBound; i++) {
                        res.add(matrix[i][rightBound]);
                    }

                    // 右边界左移
                    rightBound--;
                }
                if (upperBound <= lowerBound) {
                    // 在底部从右向左遍历
                    for (int i = rightBound; i >= leftBound; i--) {
                        res.add(matrix[lowerBound][i]);
                    }
                    // 下边界上移
                    lowerBound--;
                }
                if (leftBound <= rightBound) {
                    for (int i = lowerBound; i >= upperBound; i--) {
                        res.add(matrix[i][leftBound]);
                    }
                    // 左边界右移
                    leftBound++;
                }
            }
            return res;

        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}