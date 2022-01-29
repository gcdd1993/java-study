package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.TreeNode;

public class Num105ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        Solution solution = new Num105ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();

        TreeNode node = solution.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        node.printPreOrder();
        node.printInOrder();
        node.printPostOrder();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        }

        /**
         * 若前序遍历数组为 preorder[preStart..preEnd]，
         * 中序遍历数组为 inorder[inStart..inEnd]，
         * 构造二叉树，返回该二叉树的根节点
         *
         * <img src="https://cdn.jsdelivr.net/gh/gcdd1993/image-repo@master/img/202201300044409.png" />
         *
         * @param preorder 前序遍历
         * @param preStart 起始
         * @param preEnd   结束
         * @param inorder  中序遍历
         * @param inStart  起始
         * @param inEnd    结束
         * @return
         */
        private TreeNode build(int[] preorder, int preStart, int preEnd,
                               int[] inorder, int inStart, int inEnd) {
            if (preStart > preEnd) { // 这里需要是大于
                return null;
            }
            // 前序遍历的第一个值，即为root
            int rootValue = preorder[preStart];
            TreeNode root = new TreeNode(rootValue);

            int index = -1;
            for (int i = inStart; i <= inEnd; i++) { // 注意，inEnd，已经是最后索引的位置，而不是长度
                if (rootValue == inorder[i]) {
                    index = i;
                    break;
                }
            }
            // 左子树长度
            int leftLength = index - inStart;

            root.left = build(
                    preorder, preStart + 1, preStart + leftLength,
                    inorder, inStart, index - 1
            );
            root.right = build(
                    preorder, preStart + leftLength + 1,
                    preEnd, inorder, index + 1, inEnd
            );

            return root;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}