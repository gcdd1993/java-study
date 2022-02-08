package io.github.gcdd1993.leetcode.editor.cn;

public class Num283MoveZeroes {
    public static void main(String[] args) {
        Solution solution = new Num283MoveZeroes().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 快慢指针技巧
         * <p>
         * 先移除元素（同No.27）
         * 然后将剩余元素赋值为0
         *
         * @param nums
         */
        public void moveZeroes(int[] nums) {
            // 先将等于0的元素移除
            int slow = -1, fast = 0;
            while (fast < nums.length) {
                if (nums[fast] != 0) {
                    slow++;
                    nums[slow] = nums[fast];
                }
                fast++;
            }
            // 剩余长度赋值为0
            for (int i = slow + 1; i < nums.length; i++) {
                nums[i] = 0;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}