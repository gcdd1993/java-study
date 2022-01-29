package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.TreeNode;

public class Num104MaximumDepthOfBinaryTree {
    public static void main(String[] args) {
        Solution solution = new Num104MaximumDepthOfBinaryTree().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }

            // 利用定义，计算左右子树的最大深度
            int leftMax = maxDepth(root.left);
            int rightMax = maxDepth(root.right);
            // 整棵树的最大深度等于左右子树的最大深度取最大值，
            // 然后再加上根节点自己
            int max = Math.max(leftMax, rightMax) + 1;
            return max;
        }

//        int res = 0; // 记录最大深度
//        int depth = 0; // 记录遍历到的节点的深度
//
//        public int maxDepth(TreeNode root) {
//            traverse(root);
//            return res;
//        }
//
//        // 二叉树遍历框架
//        private void traverse(TreeNode root) {
//            if (root == null) {
//                // 到达叶子节点，更新最大深度
//                res = Math.max(res, depth);
//                return;
//            }
//            // 前序位置
//            depth++;
//            traverse(root.left);
//            // 中序位置
//            traverse(root.right);
//            // 后序位置
//            depth--;
//        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}