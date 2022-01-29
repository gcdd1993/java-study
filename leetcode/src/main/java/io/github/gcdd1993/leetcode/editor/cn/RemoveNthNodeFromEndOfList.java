package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.ListNode;

public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        Solution solution = new RemoveNthNodeFromEndOfList().new Solution();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        solution.removeNthFromEnd(head, 1);

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
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
        public ListNode removeNthFromEnd(ListNode head, int n) {
            // 虚拟头结点
            ListNode dummy = new ListNode(-1);
            dummy.next = head;

            // 删除倒数第 n 个，要先找倒数第 n + 1 个节点
            ListNode x = findFromEnd(head, n + 1);
            if (x == null) {
                return dummy.next.next;
            }
            // 删掉倒数第 n 个节点
            x.next = x.next.next;
            return dummy.next;
        }

        // 返回链表的倒数第 k 个节点
        ListNode findFromEnd(ListNode head, int k) {
            ListNode p1 = head;
            // p1 先走 k 步
            for (int i = 0; i < k; i++) {
                if (p1 == null) {
                    return null;
                }
                p1 = p1.next;
            }
            ListNode p2 = head;
            // p1 和 p2 同时走 n - k 步
            while (p1 != null) {
                p2 = p2.next;
                p1 = p1.next;
            }
            // p2 现在指向第 n - k 个节点
            return p2;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}