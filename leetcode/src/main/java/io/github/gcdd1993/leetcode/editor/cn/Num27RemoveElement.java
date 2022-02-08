package io.github.gcdd1993.leetcode.editor.cn;

public class Num27RemoveElement {
    public static void main(String[] args) {
        Solution solution = new Num27RemoveElement().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 快慢指针
         * <p>
         * 快指针遇到需要去除的元素，直接跳过，否则slow指针前进一步，并替换值
         *
         * @param nums
         * @param val
         * @return
         */
        public int removeElement(int[] nums, int val) {
            int slow = -1, fast = 0;
            while (fast < nums.length) {
                if (nums[fast] != val) {
                    slow++; // 由于slow起始位置是-1，所以需要先+1
                    nums[slow] = nums[fast]; // 将slow位置元素替换为fast位置元素
                }
                fast++;
            }
            return slow + 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}