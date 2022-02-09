package io.github.gcdd1993.leetcode.editor.cn;

public class Num172FactorialTrailingZeroes {
    public static void main(String[] args) {
        Solution solution = new Num172FactorialTrailingZeroes().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 问题分解为：n! 最多可以分解出多少个因子 5？
         *
         * @param n
         * @return
         */
        public int trailingZeroes(int n) {
            int res = 0;
            long divisor = 5;
            while (divisor <= n) {
                res += n / divisor;
                divisor *= 5;
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}