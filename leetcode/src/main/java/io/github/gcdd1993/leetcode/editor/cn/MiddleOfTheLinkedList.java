package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.ListNode;

/**
 * 我们让两个指针 slow 和 fast 分别指向链表头结点 head。
 * 每当慢指针 slow 前进一步，快指针 fast 就前进两步，这样，当 fast 走到链表末尾时，slow 就指向了链表中点。
 */
public class MiddleOfTheLinkedList {
    public static void main(String[] args) {
        Solution solution = new MiddleOfTheLinkedList().new Solution();
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
        /**
         * @param head
         * @return
         */
        public ListNode middleNode(ListNode head) {
            // 快慢指针初始化指向 head
            ListNode slow = head, fast = head;
            // 快指针走到末尾时停止
            while (fast != null && fast.next != null) {
                // 慢指针走一步，快指针走两步
                slow = slow.next;
                fast = fast.next.next;
            }

            // 慢指针指向中点
            return slow;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}