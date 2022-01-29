package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.TreeNode;

public class Num654MaximumBinaryTree {
    public static void main(String[] args) {
        Solution solution = new Num654MaximumBinaryTree().new Solution();
        solution.constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            return build(nums, 0, nums.length - 1);
        }

        private TreeNode build(int[] nums, int lo, int hi) {
            // base case：如果左索引大于右索引，证明已经遍历完整个数组
            if (lo > hi) {
                return null;
            }
            // 找到数组中的最大值和对应的索引
            int index = -1;
            int maxValue = Integer.MIN_VALUE;
            // 遍历应该遍历至hi索引结束
            for (int i = lo; i <= hi; i++) {
                if (nums[i] > maxValue) {
                    maxValue = nums[i];
                    index = i;
                }
            }
            TreeNode root = new TreeNode(maxValue);
            // 递归调用构造左右子树
            // 前序位置
            root.left = build(nums, lo, index - 1);
            // 中序位置
            root.right = build(nums, index + 1, hi);
            // 后序位置
            return root;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}