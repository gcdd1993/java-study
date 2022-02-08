package io.github.gcdd1993.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class Num1TwoSum {
    public static void main(String[] args) {
        Solution solution = new Num1TwoSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 使用哈希表降低时间复杂度（空间换时间）
         * <p>
         * 时间复杂度小于 O(n2)，时间复杂度：o(n)，空间复杂度：O(n)
         *
         * @param nums
         * @param target
         * @return
         */
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>(); // 存储数组值和下标
            for (int i = 0; i < nums.length; i++) {
                map.put(nums[i], i);
            }

            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];
                // 同一个元素在答案里不能重复出现
                if (map.containsKey(target - num) && map.get(target - num) != i) {
                    return new int[]{i, map.get(target - num)};
                }
            }
            return new int[]{-1, -1};
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}