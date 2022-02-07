package io.github.gcdd1993.leetcode.editor.cn;

public class Num1011CapacityToShipPackagesWithinDDays {
    public static void main(String[] args) {
        Solution solution = new Num1011CapacityToShipPackagesWithinDDays().new Solution();

        System.out.println("[1,2,3,1,1] 4: " + solution.shipWithinDays(new int[]{1, 2, 3, 1, 1}, 4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int shipWithinDays(int[] weights, int days) {
            int left = 0;
            int right = 1; // 1 <= weights[i] <= 500

            // 左边界应该是最小值
            for (int i = 0; i < weights.length; i++) {
                if (weights[i] > left) {
                    left = weights[i];
                }
                right += weights[i];
            }

            while (left < right) {
//                System.out.println("left: " + left + ", right: " + right);
                int mid = left + (right - left) / 2;
                int res = f(weights, mid);
                if (res == days) {
                    // 返回左边界，因为题目要求的是最低运载能力
                    right = mid;
                } else if (res > days) {
                    // 所求的天数 超过了指定天数，那么证明运载能力需要变大
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return left;
        }

        /**
         * 当运载能力为 x 时，需要 f(x) 天运完所有货物
         *
         * @param weights
         * @param x
         * @return
         */
        private int f(int[] weights, int x) {
            int days = 0;

            int y = 0;
            // 按顺序运载货物
            for (int i = 0; i < weights.length; i++) {
                y += weights[i];
                // 超过了运载能力
                if (y > x) {
                    days++;
                    y = weights[i];
                } else if (y == x) {
                    // 正好运完
                    days++;
                    y = 0;
                }
            }
            // 剩余的货物，也要一天时间
            if (y > 0) {
                days++;
            }
//            System.out.println("运载能力为: " + x + "时，需要 " + days + " 天");
            return days;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}