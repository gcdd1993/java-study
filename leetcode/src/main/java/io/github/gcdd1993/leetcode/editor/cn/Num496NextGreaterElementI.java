package io.github.gcdd1993.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Num496NextGreaterElementI {
    public static void main(String[] args) {
        Solution solution = new Num496NextGreaterElementI().new Solution();

        System.out.println(Arrays.toString(solution.nextGreaterElement(
                new int[]{4, 1, 2},
                new int[]{1, 3, 4, 2}
        )));

        System.out.println(Arrays.toString(solution.nextGreaterElement(
                new int[]{2, 4},
                new int[]{1, 2, 3, 4}
        )));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            int[] res = new int[nums1.length];
            Stack<Integer> s = new Stack<>(); // FILO
            Map<Integer, Integer> resMap = new HashMap<>(); // 记录中间结果

            // 倒着往栈里放
            for (int i = nums2.length - 1; i >= 0; i--) {
                // 如果nums[2] 中不存在比i大的数，直接从栈中抛出
                // s 里面的值是nums2的，需要比 nums1 大才能满足题意
                while (!s.empty() && s.peek() <= nums2[i]) {
                    s.pop(); // 丢弃，因为还没有当前的值大，不可能比后来的元素更大
                }

                if (!s.empty()) {
                    resMap.put(nums2[i], s.peek()); // 取出栈顶元素，即为比当前值大的元素
                } else {
                    resMap.put(nums2[i], -1);
                }

                s.push(nums2[i]);
            }

            // 组装最后结果
            for (int i = 0; i < nums1.length; i++) {
                res[i] = resMap.get(nums1[i]);
            }

            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}