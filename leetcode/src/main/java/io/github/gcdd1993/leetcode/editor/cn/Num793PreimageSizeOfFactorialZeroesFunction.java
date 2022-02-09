package io.github.gcdd1993.leetcode.editor.cn;

public class Num793PreimageSizeOfFactorialZeroesFunction {
    public static void main(String[] args) {
        Solution solution = new Num793PreimageSizeOfFactorialZeroesFunction().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int preimageSizeFZF(int k) {
            // 左边界和右边界之差 + 1 就是答案
            // 找出多少个非负整数 x ，能满足 f(x) = K
            return (int) (rightBound(k) - leftBound(k) + 1);
        }

        /**
         * 二分搜索满足 trailingZeroes(n) == target 的左边界
         *
         * @param target 目标值
         * @return
         */
        private long leftBound(int target) {
            long left = 0, right = Long.MAX_VALUE;
            while (left < right) {
                long mid = left + (right - left) / 2;
                long r = trailingZeroes(mid);
                if (r == target) {
                    // 命中答案，但是由于是求左边界，所以向左缩小范围
                    right = mid;
                } else if (r > target) {
                    // 答案在左侧
                    right = mid;
                } else {
                    // 答案在右侧
                    left = mid + 1;
                }
            }
            return left;
        }

        /**
         * 二分搜索满足 trailingZeroes(n) == target 的右边界
         *
         * @param target 目标值
         * @return
         */
        private long rightBound(int target) {
            long left = 0, right = Long.MAX_VALUE;
            while (left < right) {
                long mid = left + (right - left) / 2;
                long r = trailingZeroes(mid);
                if (r == target) {
                    // 命中答案，但是由于是求右边界，所以向右缩小范围
                    left = mid + 1;
                } else if (r > target) {
                    // 答案在左侧
                    right = mid;
                } else {
                    // 答案在右侧
                    left = mid + 1;
                }
            }
            return left - 1;
        }

        /**
         * 计算n的阶乘末尾有多少个0
         *
         * @param n
         * @return
         */
        private long trailingZeroes(long n) {
            long res = 0;
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