package io.github.gcdd1993.leetcode.editor.cn;

import java.util.Stack;

public class Num316RemoveDuplicateLetters {
    public static void main(String[] args) {
        Solution solution = new Num316RemoveDuplicateLetters().new Solution();

        // 测试用例:"bcabc"
        // 期望结果:"abc"
        System.out.println(solution.removeDuplicateLetters("bcabc"));
        System.out.println(solution.removeDuplicateLetters("bcac"));
        // "bbcaac" "bac"
        System.out.println(solution.removeDuplicateLetters("bbcaac"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 1.去除字符串中重复的字母
         * 2.返回结果的字典序最小
         * 3.保持字符顺序
         *
         * @param s
         * @return
         */
        public String removeDuplicateLetters(String s) {
            // 使用单调栈解题
            Stack<Character> stack = new Stack<>();
            boolean[] inStack = new boolean[256]; // 存储字符是否在栈中，大小为256是因为，ASCII字符0-255
            int[] count = new int[256]; // 存储元素数量

            for (char c : s.toCharArray()) {
                count[c]++;
            }
            // 先将字符入栈，从前往后遍历
            for (char c : s.toCharArray()) {
                count[c]--; // 先将字符数 - 1
                // 如果元素在栈中，就不入栈
                if (inStack[c]) {
                    continue;
                }

                // region 单调栈
                // 入栈之前，先和之前的元素比较一下大小
                // 如果字典序比前面的小，pop 前面的元素
                while (!stack.empty() && stack.peek() > c) {
                    if (count[stack.peek()] == 0) {
                        // 停止，因为后面没有这个字符了
                        break;
                    } else {
                        // pop 元素，并标记为不存在
                        inStack[stack.pop()] = false;
                    }
                }
                // endregion

                stack.push(c); // 入栈
                inStack[c] = true; // 标记为存在

            }

            // 组装结果
            StringBuilder sb = new StringBuilder();
            while (!stack.empty()) {
                sb.append(stack.pop());
            }
            // 因为出栈顺序是倒序，所以需要 reverse
            return sb.reverse().toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}