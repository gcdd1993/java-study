package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.ListNode;

/**
 * 当快慢指针相遇时，让其中任一个指针指向头节点，然后让它俩以相同速度前进，再次相遇时所在的节点位置就是环开始的位置。
 */
public class Num142LinkedListCycleIi {
    public static void main(String[] args) {
        Solution solution = new Num142LinkedListCycleIi().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     */
    public class Solution {
        public ListNode detectCycle(ListNode head) {
            // 快慢指针初始化指向 head
            ListNode slow = head, fast = head;
            // 快指针走到末尾时停止
            while (fast != null && fast.next != null) {
                // 慢指针走一步，快指针走两步
                slow = slow.next;
                fast = fast.next.next;
                if (fast == slow) {
                    break;
                }
            }
            // fast 遇到空指针说明没有环
            if (fast == null || fast.next == null) {
                return null;
            }

            // slow 重新指向头结点
            slow = head;
            // 快慢指针同步前进，相交点就是环起点

            while (slow != fast) {
                fast = fast.next;
                slow = slow.next;
            }

            return slow;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

}