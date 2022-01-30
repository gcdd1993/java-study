package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.TreeNode;

/**
 * 有效 二叉搜索树定义如下：
 * <p>
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 */
public class Num98ValidateBinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new Num98ValidateBinarySearchTree().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isValidBST(TreeNode root) {
            return isValidBST(root, null, null);
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