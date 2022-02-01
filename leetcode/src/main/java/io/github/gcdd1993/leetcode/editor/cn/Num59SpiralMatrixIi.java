package io.github.gcdd1993.leetcode.editor.cn;

public class Num59SpiralMatrixIi {
    public static void main(String[] args) {
        Solution solution = new Num59SpiralMatrixIi().new Solution();
        solution.generateMatrix(3);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 恢复螺旋矩阵
         *
         * <img src="https://img.gcdd.top/img/202201311601097.png"/>
         *
         * @param n
         * @return
         */
        public int[][] generateMatrix(int n) {
            int[][] res = new int[n][n];
            int leftBound = 0;
            int rightBound = n - 1;
            int upperBound = 0;
            int lowerBound = n - 1;
            // 记录存储的数字
            int num = 0;
            while (num < n * n) {
                if (upperBound <= lowerBound) {
                    for (int i = leftBound; i <= rightBound; i++) {
//                        System.out.println("在顶部右移 " + upperBound + ", " + i);
                        res[upperBound][i] = ++num;
//                        System.out.println("num: " + num);
                    }
                    // 上边界下移
                    upperBound++;
                }
                if (leftBound <= rightBound) {
                    for (int i = upperBound; i <= lowerBound; i++) {
//                        System.out.println("在右侧下移 " + i + ", " + rightBound);
                        res[i][rightBound] = ++num;
//                        System.out.println("num: " + num);
                    }
                    // 右边界左移
                    rightBound--;
                }
                if (upperBound <= lowerBound) {
                    for (int i = rightBound; i >= leftBound; i--) {
//                        System.out.println("在底部左移 " + lowerBound + ", " + i);
                        res[lowerBound][i] = ++num;
//                        System.out.println("num: " + num);
                    }
                    // 下边界上移
                    lowerBound--;
                }
                if (leftBound <= rightBound) {
                    for (int i = lowerBound; i >= upperBound; i--) {
//                        System.out.println("在左侧上移 " + i + ", " + leftBound);
                        res[i][leftBound] = ++num;
//                        System.out.println("num: " + num);
                    }
                    // 左边界右移
                    leftBound++;
                }
//                System.out.println(upperBound + ", " + rightBound + ", " + lowerBound + ", " + leftBound);
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}