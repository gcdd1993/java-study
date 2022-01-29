package io.github.gcdd1993.leetcode.editor.cn;

public class Num116PopulatingNextRightPointersInEachNode {
    public static void main(String[] args) {
        Solution solution = new Num116PopulatingNextRightPointersInEachNode().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public Node connect(Node root) {
            if (root == null) {
                return null;
            }
            connectTwoNode(root.left, root.right);
            return root;
        }

        /**
         * 连接两个节点
         */
        private void connectTwoNode(Node node1, Node node2) {
            if (node1 == null || node2 == null) {
                return;
            }
            // 将传入的两个节点连接
            node1.next = node2;
            // 连接相同父节点的两个子节点
            // 前序位置
            connectTwoNode(node1.left, node1.right);
            // 中序位置
            connectTwoNode(node2.left, node2.right);
            // 后序位置
            // 连接跨越父节点的两个子节点
            connectTwoNode(node1.right, node2.left);
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}