package io.github.gcdd1993.leetcode.editor.cn;

import java.util.Arrays;

public class Num34FindFirstAndLastPositionOfElementInSortedArray {
    public static void main(String[] args) {
        Solution solution = new Num34FindFirstAndLastPositionOfElementInSortedArray().new Solution();

        System.out.println("[2, 2] 3: " + Arrays.toString(solution.searchRange(new int[]{2, 2}, 3)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            if (nums.length == 0) {
                return new int[]{-1, -1};
            }
            // 定义左右指针
            int left = 0, right = nums.length - 1;
            // 确定右边界
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    left = mid + 1;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                }
            }
            int rightIndex = -1;
            if (right >= 0 && right < nums.length && nums[right] == target) {
                rightIndex = right;
            }
            // 确定左边界
            left = 0;
            right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    right = mid - 1;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                }
            }
            int leftIndex = -1;
            if (left >= 0 && left < nums.length && nums[left] == target) {
                leftIndex = left;
            }
            return new int[]{leftIndex, rightIndex};
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}