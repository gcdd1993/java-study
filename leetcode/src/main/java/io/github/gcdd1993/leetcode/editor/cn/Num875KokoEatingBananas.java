package io.github.gcdd1993.leetcode.editor.cn;

public class Num875KokoEatingBananas {
    public static void main(String[] args) {
        Solution solution = new Num875KokoEatingBananas().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minEatingSpeed(int[] piles, int h) {
            int left = 1;
            int right = 1000000000 + 1; // 1 <= piles[i] <= 10^9

            while (left < right) {
                int mid = left + (right - left) / 2;
                int res = f(piles, mid);

                if (res == h) {
                    // 求左边界
                    right = mid;
                } else if (res > h) {
                    // 时间不够，mid应该大一点
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return left;
        }

        /**
         * 速度为 x 时，需要 f(x) 小时吃完所有香蕉
         * f(x) 随着 x 的增加单调递减
         *
         * @param piles
         * @param x
         * @return
         */
        private int f(int[] piles, int x) {
            int hours = 0;
            for (int i = 0; i < piles.length; i++) {
                hours += piles[i] / x; // 每个小时，她将会选择一堆香蕉，从中吃掉 K 根
                // 如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉
                if (piles[i] % x > 0) { // 余数 大于0，也需要花费一小时吃完
                    hours++;
                }
            }
            return hours;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}