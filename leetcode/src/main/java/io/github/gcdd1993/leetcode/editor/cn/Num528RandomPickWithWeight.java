package io.github.gcdd1993.leetcode.editor.cn;

import java.util.Random;

public class Num528RandomPickWithWeight {
    public static void main(String[] args) {
        Solution solution = new Num528RandomPickWithWeight().new Solution(new int[]{1});
        System.out.println(solution.pickIndex());
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private final int[] preSum;
        private final Random random = new Random();

        public Solution(int[] w) {
            int n = w.length;
            // 构造前缀和数组
            preSum = new int[n + 1];
            preSum[0] = 0;
            for (int i = 1; i < n + 1; i++) {
                preSum[i] = preSum[i - 1] + w[i - 1];
            }
        }

        public int pickIndex() {
            int n = preSum.length;
            // target 取值范围是闭区间 [1, preSum[n - 1]]
            int target = random.nextInt(preSum[n - 1]) + 1;
            // 使用二分搜索，从前缀和数组中搜索出比 i 大的最小元素（左边界）
            int left = 0, right = n; // 这里的right是 length - 1
            while (left < right) {
                int mid = left + (right - left) / 2;
                int midValue = preSum[mid];
                if (midValue == target) {
                    right = mid;
                } else if (midValue < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            // preSum 的索引偏移了一位，还原为权重数组 w 的索引
            return left - 1;
        }
    }

    /**
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(w);
     * int param_1 = obj.pickIndex();
     */
    //leetcode submit region end(Prohibit modification and deletion)

}