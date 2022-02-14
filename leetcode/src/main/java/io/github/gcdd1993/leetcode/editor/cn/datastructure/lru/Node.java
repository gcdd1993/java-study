package io.github.gcdd1993.leetcode.editor.cn.datastructure.lru;

import lombok.Data;

/**
 * @author gcdd1993
 * @since 2022/2/13
 */
@Data
public class Node {
    public int key, val;
    public Node next, prev;

    public Node(int k, int v) {
        this.key = k;
        this.val = v;
    }
}
