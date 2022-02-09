package io.github.gcdd1993.leetcode.editor.cn;

import java.util.Arrays;

public class Num645SetMismatch {
    public static void main(String[] args) {
        Solution solution = new Num645SetMismatch().new Solution();

        System.out.println(Arrays.toString(solution.findErrorNums(new int[]{1, 2, 2, 4})));
        System.out.println(Arrays.toString(solution.findErrorNums(new int[]{2, 2})));
        System.out.println(Arrays.toString(solution.findErrorNums(new int[]{1, 2, 3, 1})));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] findErrorNums(int[] nums) {
            int n = nums.length;
            int dup = -1; // 记录重复数字
            for (int i = 0; i < n; i++) {
                // nums[i] 是当前索引位置的值
                // Math.abs(nums[i]) - 1 nums中的索引，因为是从1开始，所以要减去1

                int index = Math.abs(nums[i]) - 1; // 取出值，作为索引
                // 如果值为负数
                if (nums[index] < 0) {
                    // 2, 2
                    // 2, -2
                    dup = Math.abs(nums[i]);
                } else {
                    // 将对应的数字替换为负数
                    nums[index] *= -1;
                }
            }
            // 原数组中不是负数的即为缺失的元素
            int missing = -1;
            for (int i = 0; i < n; i++) {
                if (nums[i] > 0) {
                    missing = i + 1; // 题目是 [1,n]
                }
            }
            return new int[]{dup, missing};
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}