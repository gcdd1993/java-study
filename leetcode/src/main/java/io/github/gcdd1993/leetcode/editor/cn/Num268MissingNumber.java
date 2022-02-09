package io.github.gcdd1993.leetcode.editor.cn;

public class Num268MissingNumber {
    public static void main(String[] args) {
        Solution solution = new Num268MissingNumber().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int missingNumber(int[] nums) {
            int n = nums.length; // 数组长度
            int res = 0;
            res += n; // 因为缺失一位，所以首先加上最后一位索引
            for (int i = 0; i < n; i++) {
                res += i - nums[i];
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}