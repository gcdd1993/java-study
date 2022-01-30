package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.TreeNode;

public class Num236LowestCommonAncestorOfABinaryTree {
    public static void main(String[] args) {
        Solution solution = new Num236LowestCommonAncestorOfABinaryTree().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) {
                return null;
            }
            if (root == p || root == q) {
                return root;
            }
            // 前序遍历
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            // 中序遍历
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            // 后序遍历
            // 情况 1，如果`p`和`q`都在以`root`为根的树中，函数返回的即使`p`和`q`的最近公共祖先节点。
            // 为什么能确认root就是最近公共节点？
            // 前序遍历可以理解为是从上往下，而后序遍历是从下往上，
            // 就好比从`p`和`q`出发往上走，第一次相交的节点就是这个`root`，你说这是不是最近公共祖先呢？
            if (left != null && right != null) {
                return root;
            }
            // 情况 2，那如果`p`和`q`都不在以`root`为根的树中怎么办呢？函数理所当然地返回`null`呗。
            if (left == null && right == null) {
                return null;
            }
            // 情况 3，那如果`p`和`q`只有一个存在于`root`为根的树中呢？函数就会返回那个节点。
            return left == null ? right : left;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}