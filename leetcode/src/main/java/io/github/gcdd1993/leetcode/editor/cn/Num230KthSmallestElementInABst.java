package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.TreeNode;

/**
 * BST 的中序遍历结果是有序的（升序）
 */
public class Num230KthSmallestElementInABst {
    public static void main(String[] args) {
        Solution solution = new Num230KthSmallestElementInABst().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 记录结果
        int res = 0;
        // 记录当前元素的排名
        int index = 0;

        public int kthSmallest(TreeNode root, int k) {
            traverse(root, k);
            return res;
        }

        private void traverse(TreeNode root, int k) {
            if (root == null) {
                return;
            }
            // 前序位置
            traverse(root.left, k);
            // 中序位置 BST 的中序遍历结果是有序的（升序）
            index++;
            if (index == k) {
                res = root.val;
            }
            traverse(root.right, k);
            // 后序位置
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}