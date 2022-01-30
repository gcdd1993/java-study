package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.TreeNode;

public class Num1373MaximumSumBstInBinaryTree {
    public static void main(String[] args) {
        Solution solution = new Num1373MaximumSumBstInBinaryTree().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 全局变量，记录 BST 最大节点之和
        private int maxSum = 0;

        public int maxSumBST(TreeNode root) {
            traverse(root);
            return maxSum;
        }

        private void traverse(TreeNode root) {
            if (root == null) {
                return;
            }
            // 前序位置
            // 判断是否是 BST
            if (isBST(root)) {
                // 计算当前 BST 的节点之和
                int leftSum = findSum(root.left);
                int rightSum = findSum(root.right);
                int sum = leftSum + rightSum + root.val;
                if (sum > maxSum) {
                    maxSum = sum;
                }
            }

            traverse(root.left);
            // 中序位置
            traverse(root.right);
            // 后序位置
        }

        /**
         * 判断是否为 BST
         *
         * @param root
         * @return
         */
        private boolean isBST(TreeNode root) {
            return isValidBST(root, null, null);
        }

        /**
         * 计算 BST 的节点和
         *
         * @param root
         * @return
         */
        private int findSum(TreeNode root) {
            if (root == null) {
                return 0;
            }
            // 前序位置
            int left = findSum(root.left);
            // 中序位置
            int right = findSum(root.right);
            // 后序位置
            return left + right + root.val;
        }

        /**
         * 限定以 root 为根的子树节点必须满足 max.val > root.val > min.val
         *
         * @param root 根节点
         * @param min  最小
         * @param max  最大
         * @return
         */
        private boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
            if (root == null) {
                return true;
            }
            // 若 root.val 不符合 max 和 min 的限制，说明不是合法 BST
            if (min != null && root.val <= min.val) {
                return false;
            }
            // 节点的右子树只包含 大于 当前节点的数
            if (max != null && root.val >= max.val) {
                return false;
            }

            // 限定左子树的最大值是 root.val，右子树的最小值是 root.val
            return isValidBST(root.left, min, root) &&
                    isValidBST(root.right, root, max);

        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}