package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.TreeNode;

public class Num543DiameterOfBinaryTree {
    public static void main(String[] args) {
        Solution solution = new Num543DiameterOfBinaryTree().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {

        // 记录最大直径的长度
        int maxDiameter = 0;

        public int diameterOfBinaryTree(TreeNode root) {
            maxDepth(root);
            return maxDiameter;
        }

        /**
         * 最大深度
         *
         * @see Num104MaximumDepthOfBinaryTree
         */
        private int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            // 前序位置
            int leftMax = maxDepth(root.left);
            // 中序位置
            int rightMax = maxDepth(root.right);
            // 后序位置
            // 后序位置顺便计算最大直径
            int diameter = leftMax + rightMax;
            maxDiameter = Math.max(diameter, maxDiameter);
            return Math.max(leftMax, rightMax) + 1;
        }

//        // 最坏时间复杂度是 O(N^2)
//        // 记录最大直径的长度
//        int maxDiameter = 0;
//
//        public int diameterOfBinaryTree(TreeNode root) {
//            traverse(root);
//            return maxDiameter;
//        }
//
//        private void traverse(TreeNode root) {
//            if (root == null) {
//                return;
//            }
//            // 对每个节点计算直径
//            // 前序位置无法获取子树信息，所以只能让每个节点调用 maxDepth 函数去算子树的深度
//            int leftMax = maxDepth(root.left);
//            int rightMax = maxDepth(root.right);
//            int diameter = leftMax + rightMax;
//
//            // 更新全局最大直径
//            maxDiameter = Math.max(diameter, maxDiameter);
//
//            // 前序位置
//            traverse(root.left);
//            // 中序位置
//            traverse(root.right);
//            // 后序位置
//        }
//
//        /**
//         * 最大深度
//         *
//         * @see Num104MaximumDepthOfBinaryTree
//         */
//        private int maxDepth(TreeNode root) {
//            if (root == null) {
//                return 0;
//            }
//            // 前序位置
//            int leftMax = maxDepth(root.left);
//            // 中序位置
//            int rightMax = maxDepth(root.right);
//            // 后序位置
//            return Math.max(leftMax, rightMax) + 1;
//        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}