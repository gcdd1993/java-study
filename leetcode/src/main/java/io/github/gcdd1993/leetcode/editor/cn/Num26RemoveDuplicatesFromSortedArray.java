package io.github.gcdd1993.leetcode.editor.cn;

public class Num26RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        Solution solution = new Num26RemoveDuplicatesFromSortedArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 快慢指针技巧（快指针探路，慢指针结果）
         *
         * <img src="https://labuladong.gitee.io/algo/images/%e6%95%b0%e7%bb%84%e5%8e%bb%e9%87%8d/1.gif"/>
         *
         * @param nums
         * @return
         */
        public int removeDuplicates(int[] nums) {
            // 快慢指针技巧
            int slow = 0, fast = 0;
            while (fast < nums.length) {
                if (nums[slow] != nums[fast]) {
                    // 如果找到一个新元素，那么slow + 1，并替换fast指针的值
                    slow++;
                    nums[slow] = nums[fast];
                }
                fast++;
            }

            return slow + 1; // slow是索引位置，长度=索引+1
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}