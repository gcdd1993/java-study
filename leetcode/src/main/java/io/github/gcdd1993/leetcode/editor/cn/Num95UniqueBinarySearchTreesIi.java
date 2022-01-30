package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class Num95UniqueBinarySearchTreesIi {
    public static void main(String[] args) {
        Solution solution = new Num95UniqueBinarySearchTreesIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 处理重叠子问题
        List<TreeNode>[][] memo;

        public List<TreeNode> generateTrees(int n) {
            memo = new List[n][n];
            return generateTrees(1, n);
        }

        /**
         * 闭区间[lo, hi]的数字能组成 count(lo, hi)种 BST
         *
         * @param lo 低位
         * @param hi 高位
         * @return 数量
         */
        private List<TreeNode> generateTrees(int lo, int hi) {
            List<TreeNode> res = new LinkedList<>();
            if (lo > hi) {
                // 返回null节点
                res.add(null);
                return res;
            }

            if (memo[lo - 1][hi - 1] != null) {
                return memo[lo - 1][hi - 1];
            }

            for (int i = lo; i <= hi; i++) {
                List<TreeNode> leftNodes = generateTrees(lo, i - 1);
                List<TreeNode> rightNodes = generateTrees(i + 1, hi);
                for (TreeNode left : leftNodes) {
                    for (TreeNode right : rightNodes) {
                        TreeNode root = new TreeNode(i);
                        root.left = left;
                        root.right = right;
                        res.add(root);
                    }
                }
            }

            memo[lo - 1][hi - 1] = res;
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}