package io.github.gcdd1993.leetcode.editor.cn.datastructure.lru;

/**
 * 双向链表
 * <p>
 * 只能从尾部插入，所以tail节点是最近使用的，head节点是最久未使用的
 *
 * @author gcdd1993
 * @since 2022/2/13
 */
public class DoubleList {
    // 头尾虚节点
    private Node head, tail;
    // 链表元素数
    private int size;

    public DoubleList() {
        // 初始化双向链表的数据
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0; // 初始化长度为0
    }

    /**
     * 在链表尾部添加节点 node，时间 O(1)
     *
     * @param node
     */
    public void addLast(Node node) {
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
        size++;
    }

    /**
     * 删除链表中的 node 节点（node 一定存在）
     * 由于是双链表且给的是目标 Node 节点，时间 O(1)
     */
    public void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }

    /**
     * 删除链表中第一个节点，并返回该节点，时间 O(1)
     */
    public Node removeFirst() {
        if (head.next == tail) {
            return null;
        }
        Node first = head.next; // 取出头节点的下一个节点，因为头尾节点是初始化的，实际不属于真实节点
        remove(first);
        return first;
    }

    /**
     * 返回链表长度，时间 O(1)
     */
    public int size() {
        return this.size;
    }

}
