package io.github.gcdd1993.leetcode.editor.cn;

public class Num231PowerOfTwo {
    public static void main(String[] args) {
        Solution solution = new Num231PowerOfTwo().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 一个数如果是 2 的指数，那么它的二进制表示一定只含有一个 1
         *
         * @param n
         * @return
         */
        public boolean isPowerOfTwo(int n) {
            if (n <= 0) {
                return false;
            }
            return (n & (n - 1)) == 0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}