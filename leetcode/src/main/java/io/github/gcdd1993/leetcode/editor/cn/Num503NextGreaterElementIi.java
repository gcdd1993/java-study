package io.github.gcdd1993.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Stack;

public class Num503NextGreaterElementIi {
    public static void main(String[] args) {
        Solution solution = new Num503NextGreaterElementIi().new Solution();

        System.out.println(Arrays.toString(solution.nextGreaterElements(new int[]{1, 2, 1})));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] nextGreaterElements(int[] nums) {
            int n = nums.length;
            int[] res = new int[n];
            Stack<Integer> s = new Stack<>();
            // 使用循环数组，模拟数组循环
            for (int i = 2 * n - 1; i >= 0; i--) {
                int index = i % n; // 由于使用循环数组，所以真实的索引需要取模
                while (!s.empty() && s.peek() <= nums[index]) {
                    s.pop(); // 比当前数小的直接丢弃
                }

                if (!s.empty()) {
                    res[index] = s.peek();
                } else {
                    res[index] = -1;
                }

                s.push(nums[index]); // 入栈
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}