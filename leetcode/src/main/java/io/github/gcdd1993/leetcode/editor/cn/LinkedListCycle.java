package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.ListNode;

/**
 * 每当慢指针 slow 前进一步，快指针 fast 就前进两步。
 * 如果 fast 最终遇到空指针，说明链表中没有环；
 * 如果 fast 最终和 slow 相遇，那肯定是 fast 超过了 slow 一圈，说明链表中含有环。
 */
public class LinkedListCycle {
    public static void main(String[] args) {
        Solution solution = new LinkedListCycle().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     */
    public class Solution {
        public boolean hasCycle(ListNode head) {
            // 快慢指针初始化指向 head
            ListNode slow = head, fast = head;
            // 快指针走到末尾时停止
            while (fast != null && fast.next != null) {
                // 慢指针走一步，快指针走两步
                slow = slow.next;
                fast = fast.next.next;
                if (fast == slow) {
                    return true;
                }
            }
            return false;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
}