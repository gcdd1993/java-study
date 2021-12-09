package io.github.gcdd1993.datastructure.day02;

import io.github.gcdd1993.datastructure.day02.linear.Node;
import org.junit.jupiter.api.Test;

/**
 * @author gcdd1993
 * @since 2021/12/9
 */
class NodeTest {

    @Test
    public void test01() {
        // 构建节点
        Node<Integer> first = new Node<>(11, null);
        Node<Integer> second = new Node<>(13, null);
        Node<Integer> third = new Node<>(12, null);
        Node<Integer> fourth = new Node<>(8, null);
        Node<Integer> fifth = new Node<>(9, null);

        //生成链表
        first.setNext(second);
        second.setNext(third);
        third.setNext(fourth);
        fourth.setNext(fifth);

        System.out.println(first);
        System.out.println(second);
        System.out.println(third);
        System.out.println(fourth);
        System.out.println(fifth);
    }
}