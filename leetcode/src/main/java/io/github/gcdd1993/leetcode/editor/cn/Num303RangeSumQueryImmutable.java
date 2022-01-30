package io.github.gcdd1993.leetcode.editor.cn;

public class Num303RangeSumQueryImmutable {
    public static void main(String[] args) {
        NumArray solution = new Num303RangeSumQueryImmutable().new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class NumArray {

        // 前缀和数组
        // preSum[i] 记录 nums[0..i-1] 的累加和
        // [1, 4] 内的所有元素之和，就可以通过 preSum[5] - preSum[1] 得出
        private int[] preSum;

        public NumArray(int[] nums) {
            // preSum[0] = 0，便于计算累加和
            preSum = new int[nums.length + 1];
            // 计算累加和
            for (int i = 0; i < nums.length; i++) {
                preSum[i + 1] = preSum[i] + nums[i];
            }
        }

        /**
         * 查询闭区间 [left, right] 的累加和
         * <p>
         * [1, 4] 内的所有元素之和，就可以通过 preSum[5] - preSum[1] 得出
         *
         * @param left
         * @param right
         * @return
         */
        public int sumRange(int left, int right) {
            return preSum[right + 1] - preSum[left];
        }
    }

    /**
     * Your NumArray object will be instantiated and called as such:
     * NumArray obj = new NumArray(nums);
     * int param_1 = obj.sumRange(left,right);
     */
    //leetcode submit region end(Prohibit modification and deletion)

}