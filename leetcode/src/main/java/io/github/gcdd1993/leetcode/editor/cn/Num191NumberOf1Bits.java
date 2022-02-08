package io.github.gcdd1993.leetcode.editor.cn;

public class Num191NumberOf1Bits {
    public static void main(String[] args) {
        Solution solution = new Num191NumberOf1Bits().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        /**
         * n & (n - 1) 可以用来消除最后一个1
         *
         * @param n
         * @return
         */
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
//            int res = 0;
//            while (n != 0) {
//                n = n & (n - 1);
//                res++;
//            }
//            return res;
            // https://baike.baidu.com/item/%E6%B1%89%E6%98%8E%E9%87%8D%E9%87%8F
            n = (n & 0x55555555) + ((n >> 1) & 0x55555555); // -10101010101010101010101010101011
            n = (n & 0x33333333) + ((n >> 2) & 0x33333333); // -11001100110011001100110011001101
            n = (n & 0x0F0F0F0F) + ((n >> 4) & 0x0F0F0F0F); // -11110000111100001111000011110001
            n = (n * (0x01010101) >> 24); // -11111110111111101111111011111111
            return n;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}