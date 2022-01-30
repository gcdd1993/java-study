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

        /**
         * res[0] 记录以 root 为根的二叉树是否是 BST，若为 1 则说明是 BST，若为 0 则说明不是 BST；
         * res[1] 记录以 root 为根的二叉树所有节点中的最小值；
         * res[2] 记录以 root 为根的二叉树所有节点中的最大值；
         * res[3] 记录以 root 为根的二叉树所有节点值之和。
         *
         * @param root
         * @return
         */
        private int[] traverse(TreeNode root) {
            if (root == null) {
                // 根节点为空，也是 BST
                return new int[]{1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
            }
            // 前序位置
            int[] left = traverse(root.left);
            // 中序位置
            int[] right = traverse(root.right);
            // 后序位置
            // 判断左右子树是否为 BST
            if (left[0] == 1 && right[0] == 1) {
                int leftMax = left[2];
                int rightMin = right[1];
                // 判断 root 是否为 BST
                if (root.val > leftMax && root.val < rightMin) {
                    int sum = left[3] + right[3] + root.val;
                    if (sum > maxSum) {
                        maxSum = sum;
                    }
                    return new int[]{
                            1,
                            Math.min(left[1], root.val),
                            Math.max(right[2], root.val),
                            sum
                    };
                }
            }
            return new int[]{0, 0, 0, 0};
        }

        private int findMin(TreeNode root) {
            if (root == null) {
                return Integer.MAX_VALUE;
            }
            TreeNode p = root;
            while (p.left != null) {
                p = p.left;
            }
            return p.val;
        }

        private int findMax(TreeNode root) {
            if (root == null) {
                return Integer.MIN_VALUE;
            }
            TreeNode p = root;
            while (p.right != null) {
                p = p.right;
            }
            return p.val;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}