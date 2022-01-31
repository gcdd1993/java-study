package io.github.gcdd1993.leetcode.editor.cn;

public class Num1094CarPooling {
    public static void main(String[] args) {
        Solution solution = new Num1094CarPooling().new Solution();

        // [2,1,5],[3,5,7]
        boolean res = solution.carPooling(
                new int[][]{
                        new int[]{2, 1, 5},
                        new int[]{3, 5, 7},
                },
                3
        );
        System.out.println(res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * trips.length <= 1000
         * trips[i].length == 3
         * 1 <= trips[i][0] <= 100
         * 0 <= trips[i][1] < trips[i][2] <= 1000
         * 1 <= capacity <= 100000
         *
         * @param trips
         * @param capacity
         * @return
         */
        public boolean carPooling(int[][] trips, int capacity) {
            int[] nums = new int[1000];
            // 构造差分数组
            Difference difference = new Difference(nums);
            // [num_passengers, start_location, end_location]
            for (int[] trip : trips) {
                // 第 trip[2] 站乘客已经下车，
                // 即乘客在车上的区间是 [trip[1], trip[2] - 1]
                int i = trip[1];
                int j = trip[2] - 1;
                int val = trip[0];
                difference.increase(i, j, val);
            }

            int[] result = difference.result();
            for (int res : result) {
                if (res > capacity) {
                    return false;
                }
            }
            return true;
        }

        /**
         * 差分数组工具
         */
        public class Difference {
            private final int[] diff;

            /**
             * 从初始数组构造差分数组
             *
             * @param nums
             */
            public Difference(int[] nums) {
                diff = new int[nums.length];
                diff[0] = nums[0];
                for (int i = 1; i < nums.length; i++) {
                    diff[i] = nums[i] - nums[i - 1];
                }
            }

            public void increase(int i, int j, int val) {
                diff[i] += val;
                if (j + 1 < diff.length) {
                    diff[j + 1] -= val;
                }
            }

            public int[] result() {
                int[] res = new int[diff.length];
                res[0] = diff[0];
                for (int i = 1; i < diff.length; i++) {
                    res[i] = diff[i] + res[i - 1];
                }
                return res;
            }

        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}