package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class Num144BinaryTreePreorderTraversal {
    public static void main(String[] args) {
        Solution solution = new Num144BinaryTreePreorderTraversal().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> res = new LinkedList<>();
            // 到达叶子节点，退出
            if (root == null) {
                return res;
            }
            // 前序位置
            res.add(root.val);
            res.addAll(preorderTraversal(root.left));
            // 中序位置
            res.addAll(preorderTraversal(root.right));
            // 后序位置
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}