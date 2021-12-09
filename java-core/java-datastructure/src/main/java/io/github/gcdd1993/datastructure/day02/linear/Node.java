package io.github.gcdd1993.datastructure.day02.linear;

import lombok.Data;

/**
 * 链表节点
 *
 * @author gcdd1993
 * @since 2021/12/9
 */
@Data
public class Node<T> {

    // 存储元素
    private T item;
    // 指向下一个节点
    private Node<T> next;

    public Node(T item, Node<T> next) {
        this.item = item;
        this.next = next;
    }
}
