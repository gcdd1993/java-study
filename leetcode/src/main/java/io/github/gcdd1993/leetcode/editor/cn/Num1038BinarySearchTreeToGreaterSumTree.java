package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.TreeNode;

public class Num1038BinarySearchTreeToGreaterSumTree {
    public static void main(String[] args) {
        Solution solution = new Num1038BinarySearchTreeToGreaterSumTree().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        private int sum = 0;

        public TreeNode bstToGst(TreeNode root) {
            traverse(root);
            return root;
        }

        /**
         * 降序遍历每个节点
         *
         * @param root
         */
        private void traverse(TreeNode root) {
            if (root == null) {
                return;
            }
            // 前序位置
            traverse(root.right);
            // 中序位置
            sum += root.val;
            // 转换为累加树
            root.val = sum;
            traverse(root.left);
            // 后序位置
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}