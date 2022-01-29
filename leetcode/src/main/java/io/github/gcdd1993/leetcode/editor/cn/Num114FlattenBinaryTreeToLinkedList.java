package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.TreeNode;

public class Num114FlattenBinaryTreeToLinkedList {
    public static void main(String[] args) {
        Solution solution = new Num114FlattenBinaryTreeToLinkedList().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 不管题目如何，先把框架套上
        public void flatten(TreeNode root) {
            if (root == null) {
                return;
            }
            // 前序位置
            flatten(root.left);
            // 中序位置
            flatten(root.right);
            // 后序位置
            // 1、左右子树已经被拉平成一条链表
            TreeNode leftNode = root.left;
            TreeNode rightNode = root.right;

            // 2、将左子树作为右子树
            root.left = null;
            root.right = leftNode;

            // 3、将原先的右子树接到当前右子树的末端
            // 定位到右节点的叶子节点
            TreeNode p = root;
            while (p.right != null) {
                p = p.right;
            }
            p.right = rightNode;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}