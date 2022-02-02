package io.github.gcdd1993.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class Num3LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new Num3LongestSubstringWithoutRepeatingCharacters().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 滑动窗口算法
         *
         * @param s
         * @return
         */
        public int lengthOfLongestSubstring(String s) {
            Map<Character, Integer> window = new HashMap<>();

            int left = 0, right = 0;
            char[] chars = s.toCharArray();

            int res = 0;

            while (right < s.length()) {
                // 将要移入窗口的字符
                char c = chars[right];
                right++;

                window.put(c, window.getOrDefault(c, 0) + 1);

                // 如果字符重复
                while (window.get(c) > 1) {
                    // 将要移出窗口的字符
                    char d = chars[left];
                    left++;

                    if (window.containsKey(d)) {
                        window.put(d, window.get(d) - 1);
                        if (window.get(d) == 0) {
                            window.remove(d);
                        }
                    }
                }

                // 当字符不重复之后，更新结果
                if (right - left > res) {
                    res = right - left;
                }
            }

            return res;

        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}