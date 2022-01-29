package io.github.gcdd1993.leetcode.editor.cn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author gcdd1993
 * @since 2022/1/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    /**
     * 打印前序遍历
     */
    public void printPreOrder() {
        System.out.println(Arrays.toString(preOrder(this).toArray()));
    }

    /**
     * 打印中序遍历
     */
    public void printInOrder() {
        System.out.println(Arrays.toString(inOrder(this).toArray()));
    }

    /**
     * 打印中序遍历
     */
    public void printPostOrder() {
        System.out.println(Arrays.toString(postOrder(this).toArray()));
    }

    private List<Integer> preOrder(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        // 前序位置
        res.add(root.val);
        res.addAll(preOrder(root.left));
        // 中序位置
        res.addAll(preOrder(root.right));
        // 后序位置
        return res;
    }

    private List<Integer> inOrder(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        // 前序位置
        res.addAll(inOrder(root.left));
        // 中序位置
        res.add(root.val);
        res.addAll(inOrder(root.right));
        // 后序位置
        return res;
    }

    private List<Integer> postOrder(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        // 前序位置
        res.addAll(postOrder(root.left));
        // 中序位置
        res.addAll(postOrder(root.right));
        // 后序位置
        res.add(root.val);
        return res;
    }

}
