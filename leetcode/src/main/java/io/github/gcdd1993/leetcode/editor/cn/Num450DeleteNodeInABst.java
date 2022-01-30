package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.TreeNode;

public class Num450DeleteNodeInABst {
    public static void main(String[] args) {
        Solution solution = new Num450DeleteNodeInABst().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public TreeNode deleteNode(TreeNode root, int key) {
            if (root == null) {
                return null;
            }
            if (root.val == key) {
                // 分情况删除节点
                // 1. 两个子节点都为空，直接删除
                if (root.left == null && root.right == null) {
                    return null;
                } else if (root.left == null) {
                    // 左节点为空
                    return root.right;
                } else if (root.right == null) {
                    // 右节点为空
                    return root.left;
                } else {
                    // 左右节点都不为空，必须找到左子树中最大的那个节点，或者右子树中最小的那个节点来接替自己
                    // 1. 找到右子树中最小节点
                    TreeNode rightMin = getRightMin(root.right);
                    // 2. 替换当前节点的值
                    root.val = rightMin.val;
                    // 3. 转而去右子树删除 rightMin
                    root.right = deleteNode(root.right, rightMin.val);
                }

            }
            if (root.val < key) {
                root.right = deleteNode(root.right, key);
            }
            if (root.val > key) {
                root.left = deleteNode(root.left, key);
            }
            return root;
        }

        private TreeNode getRightMin(TreeNode right) {
            // 右子树中最左节点就是最小的
            TreeNode min = right;
            while (min.left != null) {
                min = min.left;
            }
            return min;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}