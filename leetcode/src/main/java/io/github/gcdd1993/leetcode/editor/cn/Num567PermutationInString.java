package io.github.gcdd1993.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class Num567PermutationInString {
    public static void main(String[] args) {
        Solution solution = new Num567PermutationInString().new Solution();

//        System.out.println("ab -> eidbaooo: " + solution.checkInclusion("ab", "eidbaooo"));
//        System.out.println("ab -> eidboaoo: " + solution.checkInclusion("ab", "eidboaoo"));
        System.out.println("adc -> dcda: " + solution.checkInclusion("adc", "dcda"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean checkInclusion(String s1, String s2) {
            Map<Character, Integer> need = new HashMap<>();
            Map<Character, Integer> window = new HashMap<>();

            int left = 0, right = 0;
            int valid = 0;

            char[] chars = s2.toCharArray();

            for (char c : s1.toCharArray()) {
                need.put(c, need.getOrDefault(c, 0) + 1);
            }

            while (right < chars.length) {
                // c 是将一如窗口的字符
                char c = chars[right];
                // 右移窗口
                right++;

                // 如果是目标元素，那么将其加入窗口结果内
                if (need.containsKey(c)) {
                    window.put(c, window.getOrDefault(c, 0) + 1);
                    // 如果窗口结果和题目的结果一致，那么有效数 + 1
                    if (window.get(c).equals(need.get(c))) {
                        valid++;
                    }
                }

//                System.out.printf("window1: [%d, %d)\n", left, right);

                // 排列组合，长度一致
                while (right - left == s1.length()) {
//                    System.out.printf("window2: [%d, %d)\n", left, right);
                    // 如果题目给出的长度和有效数一致，返回 成功
                    if (need.size() == valid) {
                        return true;
                    }
                    // d 是将移出窗口的字符
                    char d = chars[left];
                    // 左移窗口
                    left++;

                    // 如果窗口包含移除的元素
                    if (window.containsKey(d)) {
                        // 如果移除的元素和需要的元素数量一致，那么说明有效数 - 1
                        if (need.get(d).equals(window.get(d))) {
                            valid--;
                        }
                        window.put(d, window.get(d) - 1);
                        // 将已经为0 的窗口元素移除
                        if (window.get(d) == 0) {
                            window.remove(d);
                        }
                    }
                }
            }
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}