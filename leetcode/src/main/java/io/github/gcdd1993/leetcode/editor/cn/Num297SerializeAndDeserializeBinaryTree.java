package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;

public class Num297SerializeAndDeserializeBinaryTree {
    public static void main(String[] args) {
        Codec solution = new Num297SerializeAndDeserializeBinaryTree().new Codec();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    public class Codec {

        // 代表分隔符的字符
        private static final String SEP = ",";
        // 代表 null 空指针的字符
        private static final String NULL = "#";

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serialize(root, sb);
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            LinkedList<String> nodes = new LinkedList<>(Arrays.asList(data.split(SEP)));
            return deserialize(nodes);
        }

        private void serialize(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append(NULL).append(SEP);
                return;
            }
            // 前序位置
            sb.append(root.val).append(SEP);
            serialize(root.left, sb);
            // 中序位置
            serialize(root.right, sb);
            // 后序位置
        }

        private TreeNode deserialize(LinkedList<String> nodes) {
            if (nodes == null) {
                return null;
            }
            // 列表最左侧就是根节点
            String head = nodes.poll();
            if (NULL.equals(head)) {
                return null;
            }
            int value = Integer.parseInt(head);
            TreeNode root = new TreeNode(value);
            // 前序位置
            root.left = deserialize(nodes);
            // 中序位置
            root.right = deserialize(nodes);
            // 后序位置
            return root;
        }
    }

    // Your Codec object will be instantiated and called as such:
    // Codec ser = new Codec();
    // Codec deser = new Codec();
    // TreeNode ans = deser.deserialize(ser.serialize(root));
    //leetcode submit region end(Prohibit modification and deletion)

}