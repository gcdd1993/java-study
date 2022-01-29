package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://labuladong.gitee.io/algo/2/17/16/
 * <p>
 * 优先队列 pq 中的元素个数最多是 k，所以一次 poll 或者 add 方法的时间复杂度是 O(logk)；
 * 所有的链表节点都会被加入和弹出 pq，所以算法整体的时间复杂度是 O(Nlogk)，其中 k 是链表的条数，N 是这些链表的节点总数。
 */
public class MergeKSortedLists {
    public static void main(String[] args) {
        Solution solution = new MergeKSortedLists().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists.length == 0) {
                return null;
            }

            // 虚拟头结点
            ListNode dummy = new ListNode(-1);
            ListNode p = dummy;

            // 优先级队列，最小堆
            PriorityQueue<ListNode> pq =
                    new PriorityQueue<>(lists.length, Comparator.comparingInt(v -> v.val));

            // 将 k 个链表的头结点加入最小堆
            for (ListNode head : lists) {
                if (head != null) {
                    pq.add(head);
                }
            }

            while (!pq.isEmpty()) {
                // 获取最小节点，接到结果链表中
                ListNode node = pq.poll();
                p.next = node;
                if (node.next != null) {
                    pq.add(node.next);
                }
                // p 指针不断前进
                p = p.next;
            }

            return dummy.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}