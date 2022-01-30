package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.TreeNode;

public class Num700SearchInABinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new Num700SearchInABinarySearchTree().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public TreeNode searchBST(TreeNode root, int val) {
            if (root == null) {
                return null;
            }
            // 找到了，直接返回
            if (root.val == val) {
                return root;
            }
            // BST 左小右大
            if (root.val < val) {
                // 去右子树查询
                return searchBST(root.right, val);
            }
            // 去左子树查询
            return searchBST(root.left, val);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}