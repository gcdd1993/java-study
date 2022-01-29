package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.ListNode;

/**
 * https://labuladong.gitee.io/algo/2/17/16/
 */
public class MergeTwoSortedLists {
    public static void main(String[] args) {
        Solution solution = new MergeTwoSortedLists().new Solution();
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
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            // 虚拟头结点
            // 如果不使用 dummy 虚拟节点，代码会复杂很多，
            // 而有了 dummy 节点这个占位符，可以避免处理空指针的情况，降低代码的复杂性。
            ListNode dummy = new ListNode(-1), p = dummy;
            ListNode p1 = list1, p2 = list2;
            while (p1 != null && p2 != null) {
                // 比较 p1 和 p2 两个指针
                // 将值较小的的节点接到 p 指针
                if (p1.val > p2.val) {
                    p.next = p2;
                    p2 = p2.next;
                } else {
                    p.next = p1;
                    p1 = p1.next;
                }
                // p 指针不断前进
                p = p.next;
            }
            if (p1 != null) {
                p.next = p1;
            }

            if (p2 != null) {
                p.next = p2;
            }

            return dummy.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}