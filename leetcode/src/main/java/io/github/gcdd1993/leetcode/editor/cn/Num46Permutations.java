package io.github.gcdd1993.leetcode.editor.cn;

import java.util.LinkedList;
import java.util.List;

public class Num46Permutations {
    public static void main(String[] args) {
        Solution solution = new Num46Permutations().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 保存结果
         */
        private final List<List<Integer>> res = new LinkedList<>();

        /**
         * 回溯算法（暴力穷举）
         * <p>
         * 决策树的遍历问题
         *
         * @param nums
         * @return
         */
        public List<List<Integer>> permute(int[] nums) {
            // 记录 路径
            LinkedList<Integer> track = new LinkedList<>();
            backtrack(nums, track);
            return res;
        }

        /**
         * 路径：记录在 track 中
         * 选择列表：nums 中不存在于 track 的那些元素
         * 结束条件：nums 中的元素全都在 track 中出现
         *
         * @param nums
         * @param track
         */
        private void backtrack(int[] nums, LinkedList<Integer> track) {
            // 触发结束条件
            if (track.size() == nums.length) {
                res.add(new LinkedList<>(track));
                return;
            }

            for (int num : nums) {
                // 排除不合法的选择
                if (track.contains(num)) {
                    continue;
                }
                // 做选择
                track.add(num);
                // 进入下一层决策树
                backtrack(nums, track);
                // 取消选择
                track.removeLast();
            }
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}