package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.TreeNode;

public class Num106ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public static void main(String[] args) {
        Solution solution = new Num106ConstructBinaryTreeFromInorderAndPostorderTraversal().new Solution();

        TreeNode node = solution.buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});

        node.printInOrder();
        node.printPostOrder();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
        }


        /**
         * 后序遍历数组为 postorder[postStart..postEnd]，
         * 中序遍历数组为 inorder[inStart..inEnd]，
         * 构造二叉树，返回该二叉树的根节点
         *
         * <img src ="https://cdn.jsdelivr.net/gh/gcdd1993/image-repo@master/img/202201300043803.png" />
         *
         * @param inorder   中序遍历
         * @param inStart   起始
         * @param inEnd     结束
         * @param postorder 后序遍历
         * @param postStart 起始
         * @param postEnd   结束
         * @return
         */
        private TreeNode build(int[] inorder, int inStart, int inEnd,
                               int[] postorder, int postStart, int postEnd) {
            // base case
            if (postStart > postEnd) {
                return null;
            }
            // 根节点对应的值为 postorder 的最后一个元素
            int rootValue = postorder[postEnd];
            int index = -1;
            for (int i = inStart; i <= inEnd; i++) {
                if (rootValue == inorder[i]) {
                    index = i;
                    break;
                }
            }

            int leftLength = index - inStart;

            TreeNode root = new TreeNode(rootValue);
            root.left = build(
                    inorder, inStart, index - 1, postorder,
                    postStart, postStart + leftLength - 1
            );
            root.right = build(
                    inorder, index + 1, inEnd, postorder,
                    postStart + leftLength, postEnd - 1
            );

            return root;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}