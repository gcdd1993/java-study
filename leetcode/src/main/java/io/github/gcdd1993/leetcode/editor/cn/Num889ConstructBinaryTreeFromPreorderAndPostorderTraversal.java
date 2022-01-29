package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.TreeNode;

public class Num889ConstructBinaryTreeFromPreorderAndPostorderTraversal {
    public static void main(String[] args) {
        Solution solution = new Num889ConstructBinaryTreeFromPreorderAndPostorderTraversal().new Solution();
        TreeNode node = solution.constructFromPrePost(new int[]{1, 2, 4, 5, 3, 6, 7}, new int[]{4, 5, 2, 6, 7, 3, 1});
        node.printPreOrder();
        node.printPostOrder();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
            return build(preorder, 0, preorder.length - 1, postorder, 0, postorder.length - 1);
        }

        /**
         * 定义：根据 preorder[preStart..preEnd] 和 postorder[postStart..postEnd]
         * 构建二叉树，并返回根节点。
         * <img src="https://cdn.jsdelivr.net/gh/gcdd1993/image-repo@master/img/202201300050605.png"/>
         *
         * @param preorder  前序遍历
         * @param preStart  起始
         * @param preEnd    结束
         * @param postorder 后序遍历
         * @param postStart 起始
         * @param postEnd   结束
         * @return
         */
        private TreeNode build(int[] preorder, int preStart, int preEnd,
                               int[] postorder, int postStart, int postEnd) {
            if (preStart > preEnd) {
                return null;
            }

            // 只有一个节点，则直接返回根节点
            if (preStart == preEnd) {
                return new TreeNode(preorder[preStart]);
            }

            // 前序遍历第一个值，即为root
            int rootValue = preorder[preStart];
            int leftRootValue = preorder[preStart + 1];
            int index = -1;

            for (int i = postStart; i <= postEnd; i++) {
                if (leftRootValue == postorder[i]) {
                    index = i;
                    break;
                }
            }

            // 获取左子树长度
            int leftSize = index - postStart + 1;
            TreeNode root = new TreeNode(rootValue);
            root.left = build(
                    preorder, preStart + 1, preStart + leftSize,
                    postorder, postStart, index
            );
            root.right = build(
                    preorder, preStart + leftSize + 1, preEnd,
                    postorder, index + 1, postEnd - 1
            );

            return root;
        }


    }
    //leetcode submit region end(Prohibit modification and deletion)

}