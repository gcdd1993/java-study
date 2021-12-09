package io.github.gcdd1993.datastructure.day02.linear;

import java.util.Iterator;
import java.util.Objects;

/**
 * 顺序表
 *
 * @author gcdd1993
 * @since 2021/12/8
 */
public class SequenceList<T> implements Iterable<T> {
    // 存储元素的数组
    private T[] elements;
    // 记录当前顺序表中的元素个数
    private int N;

    /**
     * 创建容量为capacity的SequenceList对象
     */
    @SuppressWarnings("unchecked")
    public SequenceList(int capacity) {
        // 初始化数组
        this.elements = (T[]) new Object[capacity];
        // 初始化长度
        N = 0;
    }

    /**
     * 空置线性表
     */
    public void clear() {
        N = 0;
    }

    /**
     * 判断线性表是否为空，是返回true，否返回false
     *
     * @return
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 获取线性表中元素的个数
     *
     * @return
     */
    public int length() {
        return N;
    }

    /**
     * 读取并返回线性表中的第i个元素的值
     *
     * @param i
     * @return
     */
    public T get(int i) {
        return elements[i];
    }

    /**
     * 在线性表的第i个元素之前插入一个值为t的数据元素
     *
     * @param i
     * @param t
     */
    public void insert(int i, T t) {
        // 扩容
        if (N == elements.length) {
            resize(2 * elements.length);
        }

        // 先把i索引处的元素及其后面的元素依次向后移动一位
        for (int j = N; j > i; j--) {
            elements[j] = elements[j - 1];
        }
        // 再把t元素放到i索引处
        elements[i] = t;
        N++;
    }

    /**
     * 向线性表中添加一个元素t
     *
     * @param t
     */
    public void insert(T t) {
        // 扩容
        if (N == elements.length) {
            resize(2 * elements.length);
        }

        elements[N++] = t;
    }

    /**
     * 删除并返回线性表中第i个数据元素
     *
     * @param i
     * @return
     */
    public T remove(int i) {

        // 记录i索引处的值
        T current = elements[i];

        // 索引i后面元素依次向前移动一位
        for (int j = i; j < N - 1; j++) {
            elements[j] = elements[j + 1];
        }
        // 元素个数-1
        N--;

        // 缩容
        if (N < elements.length / 4) {
            resize(elements.length / 2);
        }
        return current;
    }

    /**
     * 返回线性表中首次出现的指定的数据元素的位序号，若不存在，则返回-1。
     *
     * @param t
     * @return
     */
    public int indexOf(T t) {
        // 扩容
        if (N == elements.length) {
            resize(2 * elements.length);
        }

        for (int i = 0; i < N; i++) {
            if (Objects.equals(elements[i], t)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据newSize，重置elements的大小
     *
     * @param newSize
     */
    @SuppressWarnings("unchecked")
    public void resize(int newSize) {
        // 定义一个临时数组，指向原数组
        T[] temp = elements;
        // 创建新数组
        elements = (T[]) new Object[newSize];
        for (int i = 0; i < temp.length; i++) {
            elements[i] = temp[i];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new SIterator();
    }

    private class SIterator implements Iterator<T> {
        private int cursor;

        public SIterator() {
            this.cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return cursor < N;
        }

        @Override
        public T next() {
            return elements[cursor++];
        }
    }
}
