package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Num652FindDuplicateSubtrees {
    public static void main(String[] args) {
        Solution solution = new Num652FindDuplicateSubtrees().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        // 记录所有子树
        Map<String, Integer> memo = new HashMap<>();
        // 记录重复的子树根节点
        List<TreeNode> res = new LinkedList<>();

        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            traverse(root);
            return res;
        }

        /**
         * 二叉树序列化套路
         *
         * @param root
         * @return
         */
        private String traverse(TreeNode root) {
            if (root == null) {
                return "#";
            }
            // 前序位置
            String left = traverse(root.left);
            // 中序位置
            String right = traverse(root.right);
            // 后序位置
            String subTree = left + "," + right + "," + root.val;

            Integer freq = memo.getOrDefault(subTree, 0);
            // 只有第一次重复，才加入结果集，保证结果集不重复
            if (freq == 1) {
                // 有人和我重复，把自己加入结果列表
                res.add(root);
            }
            // 出现次数 + 1
            memo.put(subTree, freq + 1);
            return subTree;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}