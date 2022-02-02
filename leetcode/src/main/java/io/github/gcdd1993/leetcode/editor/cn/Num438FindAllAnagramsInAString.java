package io.github.gcdd1993.leetcode.editor.cn;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Num438FindAllAnagramsInAString {
    public static void main(String[] args) {
        Solution solution = new Num438FindAllAnagramsInAString().new Solution();

        System.out.println("cbaebabacd -> abc: " + solution.findAnagrams("cbaebabacd", "abc"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            Map<Character, Integer> need = new HashMap<>();
            Map<Character, Integer> window = new HashMap<>();

            int left = 0, right = 0;
            int valid = 0;

            List<Integer> res = new LinkedList<>();

            char[] chars = s.toCharArray();
            for (char c : p.toCharArray()) {
                need.put(c, need.getOrDefault(c, 0) + 1);
            }

            while (right < chars.length) {
                // c 是将移入窗口的字符
                char c = chars[right];
                // 右移窗口
                right++;

                if (need.containsKey(c)) {
                    window.put(c, window.getOrDefault(c, 0) + 1);
                    if (window.get(c).equals(need.get(c))) {
                        valid++;
                    }
                }

                // 长度不能超过 p
                while (right - left >= p.length()) {
                    if (valid == need.size()) {
                        // 添加结果
                        res.add(left);
                    }
                    // d 是将移出窗口的字符
                    char d = chars[left];
                    // 如果将要移出窗口的字符是 题目给出的字符
                    if (window.containsKey(d)) {
                        if (window.get(d).equals(need.get(d))) {
                            valid--;
                        }
                        window.put(d, window.get(d) - 1);
                        if (window.get(d) == 0) {
                            window.remove(d);
                        }
                    }

                    // 左指针右移
                    left++;

                }

            }

            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}