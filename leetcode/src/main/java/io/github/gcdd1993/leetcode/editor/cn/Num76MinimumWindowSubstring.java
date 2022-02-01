package io.github.gcdd1993.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class Num76MinimumWindowSubstring {
    public static void main(String[] args) {
        Solution solution = new Num76MinimumWindowSubstring().new Solution();

        System.out.println("ADOBECODEBANC -> ABC: " + solution.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println("a -> aa: " + solution.minWindow("a", "aa"));
        System.out.println("ab -> a: " + solution.minWindow("ab", "a"));
        System.out.println("ADOBECODEBANC -> ABC: " + solution.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println("cabwefgewcwaefgcf -> cae: " + solution.minWindow("cabwefgewcwaefgcf", "cae"));
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 滑动窗口
     */
    class Solution {

        /**
         * 思路：
         * 1、我们在字符串 `S` 中使用双指针中的左右指针技巧，初始化 `left = right = 0`，把索引**左闭右开**区间 `[left, right)` 称为一个「窗口」。
         * 2、我们先不断地增加 `right` 指针扩大窗口 `[left, right)`，直到窗口中的字符串符合要求（包含了 `T` 中的所有字符）。
         * 3、此时，我们停止增加 `right`，转而不断增加 `left` 指针缩小窗口 `[left, right)`，直到窗口中的字符串不再符合要求（不包含 `T` 中的所有字符了）。同时，每次增加 `left`，我们都要更新一轮结果。
         * 4、重复第 2 和第 3 步，直到 `right` 到达字符串 `S` 的尽头。
         *
         * @param s
         * @param t
         * @return
         */
        public String minWindow(String s, String t) {
            Map<Character, Integer> need = new HashMap<>();
            Map<Character, Integer> window = new HashMap<>();

            // 先初始化 T 的字符集
            for (char c : t.toCharArray()) {
                need.put(c, need.getOrDefault(c, 0) + 1);
            }

            int left = 0, right = 0;
            int valid = 0;

            char[] chars = s.toCharArray();

            // 循环结束时，左/右指针分别会多走一步，所以这里需要减去1
            // Java里面substring的定义，第二个参数是一个开区间，所以 right - 1 + 1，得到的就是 right
            int startIndex = -1;
            int len = Integer.MAX_VALUE;

            while (right < chars.length) {
                // c 是将移入窗口的字符
                char c = chars[right];
                // 右移窗口
                right++;

                if (need.containsKey(c)) {
                    window.put(c, window.getOrDefault(c, 0) + 1);
                    // 如果满足字符数，则该字符满足条件
                    if (window.get(c).equals(need.get(c))) {
                        valid++;
                    }
                }

//                System.out.printf("window: [%d, %d), valid: %d, need: %d\n", left, right, valid, need.size());

                // 判断左侧窗口是否要收缩
                while (valid == need.size() && left < right) {
                    // 更新结果索引
                    if (right - left < len) {
                        startIndex = left;
                        len = right - left;
                    }
                    // d 是将移出窗口的字符
                    char d = chars[left];
                    // 左移窗口
                    left++;
                    if (window.containsKey(d)) {
                        if (window.get(d).equals(need.get(d))) {
                            valid--;
                        }
                        window.put(d, window.get(d) - 1);
                        if (window.get(d) == 0) {
                            window.remove(d);
                        }
                    }
                }
            }

            return Integer.MAX_VALUE != len ? s.substring(startIndex, startIndex + len) : "";

        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}