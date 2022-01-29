package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.TreeNode;

public class Num226InvertBinaryTree {
    public static void main(String[] args) {
        Solution solution = new Num226InvertBinaryTree().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public TreeNode invertTree(TreeNode root) {
            // 只要把二叉树上的每一个节点的左右子节点进行交换，最后的结果就是完全翻转之后的二叉树
            if (root == null) {
                return null;
            }
            // 前序位置
            TreeNode leftNode = invertTree(root.left);
            // 中序位置
            TreeNode rightNode = invertTree(root.right);
            // 后序位置
            root.left = rightNode;
            root.right = leftNode;
            return root;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}