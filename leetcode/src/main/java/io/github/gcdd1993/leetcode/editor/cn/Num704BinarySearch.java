package io.github.gcdd1993.leetcode.editor.cn;

public class Num704BinarySearch {
    public static void main(String[] args) {
        Solution solution = new Num704BinarySearch().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int search(int[] nums, int target) {
            int left = 0, right = nums.length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2; // 为什么不写成 (left + right) / 2，是为了处理left和right太大直接相加导致溢出
                if (nums[mid] == target) {
                    // 找到结果
                    return mid;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                }
            }
            return -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}