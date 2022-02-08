package io.github.gcdd1993.leetcode.editor.cn;

public class Num136SingleNumber {
    public static void main(String[] args) {
        Solution solution = new Num136SingleNumber().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 一个数和它本身做异或运算结果为 0，即 a ^ a = 0
         * 一个数和 0 做异或运算的结果为它本身，即 a ^ 0 = a
         *
         * @param nums
         * @return
         */
        public int singleNumber(int[] nums) {
            int res = 0;
            for (int num : nums) {
                res ^= num; // 成对的元素异或之后变为0，孤立的元素与0异或还是本身
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}