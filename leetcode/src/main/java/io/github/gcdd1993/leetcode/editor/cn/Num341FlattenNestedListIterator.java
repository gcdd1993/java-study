package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.NestedInteger;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Num341FlattenNestedListIterator {
    public static void main(String[] args) {
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 惰性解法
     */
    public class NestedIterator implements Iterator<Integer> {

        private final LinkedList<NestedInteger> list;

        public NestedIterator(List<NestedInteger> nestedList) {
            // 不直接使用 nestedList，是不确定他的底层实现
            // 必须保证是 LinkedList，否则addFirst会很低效（如果底层是数组，那么添加到索引0位置，每次都会进行数组拷贝）
            list = new LinkedList<>(nestedList);
        }

        @Override
        public boolean hasNext() {
            // 循环拆分列表元素，直到列表第一个元素是整数类型
            while (!list.isEmpty() && !list.get(0).isInteger()) {
                // 当列表开头第一个元素是列表类型时，进入循环
                List<NestedInteger> first = this.list.remove(0).getList();
                // 将第一个列表打平并按顺序添加到开头
                for (int i = first.size() - 1; i >= 0; i--) {
                    list.addFirst(first.get(i));
                }
            }
            return !list.isEmpty();
        }

        @Override
        public Integer next() {
            return list.remove(0).getInteger();
        }
    }

//    /**
//     * 等价于遍历一棵 N 叉树的所有「叶子节点」
//     * <p>
//     * 如果输入的规模非常大，构造函数中的计算就会很慢，而且很占用内存。
//     */
//    public class NestedIterator implements Iterator<Integer> {
//
//        private final Iterator<Integer> it;
//
//        public NestedIterator(List<NestedInteger> nestedList) {
//            List<Integer> result = new LinkedList<>();
//
//            nestedList.forEach(r -> traverse(r, result));
//
//            it = result.iterator();
//        }
//
//        @Override
//        public Integer next() {
//            return it.next();
//        }
//
//        @Override
//        public boolean hasNext() {
//            return it.hasNext();
//        }
//
//        // 遍历以 root 为根的N叉树，将叶子节点的值加入result列表
//        private void traverse(NestedInteger root, List<Integer> result) {
//            if (root.isInteger()) {
//                result.add(root.getInteger());
//                return;
//            }
//            for (NestedInteger child : root.getList()) {
//                traverse(child, result);
//            }
//        }
//    }

    /**
     * Your NestedIterator object will be instantiated and called as such:
     * NestedIterator i = new NestedIterator(nestedList);
     * while (i.hasNext()) v[f()] = i.next();
     */
    //leetcode submit region end(Prohibit modification and deletion)

}