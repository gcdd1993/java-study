package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.ListNode;

public class ReverseLinkedListIi {
    public static void main(String[] args) {
        Solution solution = new ReverseLinkedListIi().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     */
    class Solution {
        public ListNode reverseBetween(ListNode head, int left, int right) {

        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 递归算法
     */
    class Solution1 {
        ListNode successor = null; // 后驱节点

        public ListNode reverseBetween(ListNode head, int left, int right) {
            // base case
            if (left == 1) {
                // 相当于反转前 n 个元素
                return reverseN(head, right);
            }
            // 前进到反转的起点触发 base case
            head.next = reverseBetween(head.next, left - 1, right - 1);
            return head;
        }

        /**
         * 反转链表前 N 个节点
         */
        private ListNode reverseN(ListNode head, int n) {
            if (n == 1) {
                // 记录第 n + 1 个节点
                successor = head.next;
                return head;
            }
            // 以 head.next 为起点，需要反转前 n - 1 个节点
            ListNode last = reverseN(head, n - 1);
            head.next.next = head;
            // 让反转之后的 head 节点和后面的节点连起来
            head.next = successor;
            return last;
        }
    }

}