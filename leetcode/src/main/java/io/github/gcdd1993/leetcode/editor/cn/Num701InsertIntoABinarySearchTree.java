package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.TreeNode;

public class Num701InsertIntoABinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new Num701InsertIntoABinarySearchTree().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // BST 特点是左小右大
        public TreeNode insertIntoBST(TreeNode root, int val) {
            // 找到空位置插入新节点
            if (root == null) {
                return new TreeNode(val);
            }
            // 如果大于根节点，应该插入到右子树
            if (root.val < val) {
                root.right = insertIntoBST(root.right, val);
            }
            // 如果小于根节点，应该插入到左子树
            if (root.val > val) {
                root.left = insertIntoBST(root.left, val);
            }
            return root;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}