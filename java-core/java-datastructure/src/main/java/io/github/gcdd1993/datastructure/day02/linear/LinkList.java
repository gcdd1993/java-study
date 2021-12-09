package io.github.gcdd1993.datastructure.day02.linear;

import java.util.Iterator;

/**
 * 单向链表
 *
 * @author gcdd1993
 * @since 2021/12/9
 */
public class LinkList<T> implements Iterable<T> {

    // 记录首节点
    private Node<T> head;
    // 记录链表的长度
    private int N;

    public LinkList() {
        // 初始化头结点
        head = new Node<>(null, null);
        // 初始化元素个数
        N = 0;
    }

    /**
     * 空置线性表
     */
    public void clear() {
        head.next = null;
        N = 0;
    }

    /**
     * 判断线性表是否为空，是返回true，否返回false
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 获取线性表中元素的个数
     */
    public int length() {
        return N;
    }

    /**
     * 读取并返回线性表中的第i个元素的值
     */
    public T get(int i) {
        if (i < 0 || i >= N) {
            throw new RuntimeException("位置不合法！");
        }
        // 通过循环，从头结点开始往后找，依次找i次，就可以找到对应的元素
        Node<T> n = head.next;
        for (int index = 0; index < i; index++) {
            n = n.next;
        }
        return n.item;
    }

    /**
     * 往线性表中添加一个元素
     */
    public void insert(T t) {
        // 找到当前最后一个节点
        Node<T> n = head; // 头节点不存储数据，所以不能算作第一个元素
        while (n.next != null) {
            n = n.next;
        }
        // 创建新节点，保存元素t
        Node<T> newNode = new Node<>(t, null);
        // 让当前最后一个元素指向新节点
        n.next = newNode;
        // 元素个数+1
        N++;
    }

    /**
     * 在线性表的第i个元素之前插入一个值为t的数据元素
     */
    public void insert(int i, T t) {
        if (i < 0 || i >= N) {
            throw new RuntimeException("位置不合法！");
        }

        // 找到i位置前一个节点
        Node<T> pre = head; // 头节点不存储数据，所以不能算作第一个元素
        for (int index = 0; index < i; index++) {
            pre = pre.next;
        }

        // 找到i位置的节点
        Node<T> current = pre.next;

        // 创建新节点，并且新节点需要指向原来i位置的节点
        Node<T> newNode = new Node<>(t, current);

        // 原来i位置的前一个节点指向新节点
        pre.next = newNode;

        // 元素个数+1
        N++;
    }

    /**
     * 删除并返回线性表中第i个数据元素
     */
    public T remove(int i) {
        if (i < 0 || i >= N) {
            throw new RuntimeException("位置不合法");
        }

        // 找到i位置前一个节点
        Node<T> pre = head;
        for (int index = 0; index < i; index++) {
            pre = pre.next;
        }

        // 找到i位置的节点
        Node<T> current = pre.next;

        // 找到i位置的下一个节点
        Node<T> next = current.next;

        // 前一个节点指向下一个节点
        pre.next = next;

        // 元素个数 - 1
        N--;
        return current.item;
    }

    /**
     * 返回线性表中首次出现的指定的数据元素的位序号，若不存在，则返回-1
     */
    public int indexOf(T t) {
        // 从头结点开始，依次找到每一个节点，取出item，和t比较，如果相同，就返回
        Node<T> n = head;
        for (int i = 0; n.next != null; i++) {
            n = n.next;
            if (n.item.equals(t)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new LIterator();
    }

    private static class Node<T> {

        // 存储元素
        T item;
        // 指向下一个节点
        Node<T> next;

        Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }

    private class LIterator implements Iterator<T> {
        private Node<T> n = head;

        @Override
        public boolean hasNext() {
            return n.next != null;
        }

        @Override
        public T next() {
            n = n.next;
            return n.item;
        }
    }

}
