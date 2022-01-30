package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.TreeNode;

public class Num222CountCompleteTreeNodes {
    public static void main(String[] args) {
        Solution solution = new Num222CountCompleteTreeNodes().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int countNodes(TreeNode root) {
            if (root == null) {
                return 0;
            }

            // 记录左、右子树的高度
            TreeNode left = root, right = root;
            int leftHi = 0;
            int rightHi = 0;
            while (left != null) {
                left = left.left;
                leftHi++;
            }
            while (right != null) {
                right = right.right;
                rightHi++;
            }

            // 如果左右子树的高度相同，则是一棵满二叉树
            if (leftHi == rightHi) {
                // 满二叉树不需要递归，直接用 2^子树高度 - 1可以得到节点数量
                return (int) Math.pow(2, leftHi) - 1;
            }

            // 如果左右高度不同，则按照普通二叉树的逻辑计算
            int leftSize = countNodes(root.left);
            int rightSize = countNodes(root.right);
            return leftSize + rightSize + 1;

            // 如果是一棵满二叉树，节点总数就和树的高度呈指数关系
//            int hi = 0;
//            // 计算树的高度
//            TreeNode p = root;
//            while (p != null) {
//                hi++;
//                p = p.left;
//            }
//            // 节点总数就是 2^h - 1
//            return (int) Math.pow(2, hi) - 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}