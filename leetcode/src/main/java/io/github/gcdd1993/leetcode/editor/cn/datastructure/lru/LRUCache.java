package io.github.gcdd1993.leetcode.editor.cn.datastructure.lru;

import java.util.HashMap;

/**
 * 最近最少使用缓存
 * <p>
 * Least Recently Used
 * <p>
 * 特性：
 * 1、显然 cache 中的元素必须有时序，以区分最近使用的和久未使用的数据，当容量满了之后要删除最久未使用的那个元素腾位置。
 * 2、我们要在 cache 中快速找某个 key 是否已存在并得到对应的 val；
 * 3、每次访问 cache 中的某个 key，需要将这个元素变为最近使用的，也就是说 cache 要支持在任意位置快速插入和删除元素。
 *
 * @author gcdd1993
 * @since 2022/2/12
 */
public class LRUCache {

    /**
     * key -> Node(key, val)
     */
    private HashMap<Integer, Node> map;

    /**
     * Node(k1, v1) <-> Node(k2, v2)...
     */
    private DoubleList cache;

    // 最大容量
    private int cap;

    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    public int get(int key) {
        // 使用Hash表，保证判断节点是否存在时间复杂度为 O(1)
        if (!map.containsKey(key)) {
            return -1;
        }
        // 将该数据提升为最近使用的
        makeRecently(key);
        return map.get(key).val;
    }

    public void put(int key, int val) {
        if (map.containsKey(key)) {
            // 删除旧的数据
            deleteKey(key);
            // 新插入的数据为最近使用
            addRecently(key, val);
            return;
        }

        // 如果容量满了
        if (cap == cache.size()) {
            // 删除最久未使用的数据
            removeLeastRecently();
        }
        // 添加为最近使用的元素
        addRecently(key, val);
    }

    /**
     * 将某个 key 提升为最近使用的
     *
     * @param key
     */
    private void makeRecently(int key) {
        Node node = map.get(key);
        // 先从链表中删除这个节点
        cache.remove(node);
        // 重新插到队尾
        cache.addLast(node);
    }

    /**
     * 添加最近使用的元素
     *
     * @param key
     * @param val
     */
    private void addRecently(int key, int val) {
        Node node = new Node(key, val);
        // 链表尾部就是最近使用的元素
        cache.addLast(node);
        // 别忘了在 map 中添加 key 的映射
        map.put(key, node);
    }

    /**
     * 删除某一个 key
     *
     * @param key
     */
    private void deleteKey(int key) {
        Node node = map.get(key);
        // 从链表中删除
        cache.remove(node);
        // 从 map 中删除
        map.remove(key);
    }

    /**
     * 删除最久未使用的元素
     */
    private void removeLeastRecently() {
        // 链表头部的第一个元素就是最久未使用的
        Node node = cache.removeFirst();
        // 同时别忘了从 map 中删除它的 key
        int key = node.key;
        map.remove(key);
    }

}
