package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.ListNode;

public class Num83RemoveDuplicatesFromSortedList {
    public static void main(String[] args) {
        Solution solution = new Num83RemoveDuplicatesFromSortedList().new Solution();

        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {

        /**
         * 快慢指针技巧
         *
         * @param head
         * @return
         */
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode slow = head, fast = head;
            while (fast != null) {
                // 如果快指针和慢指针不一致，那么找到了新元素，将慢指针前进一步，并替换慢指针
                if (slow.val != fast.val) {
                    // 替换慢指针
                    slow.next = fast;
                    // slow ++
                    slow = slow.next;
                }
                fast = fast.next;
            }
            slow.next = null; // 截断后面的部分
            // 不能返回慢指针，因为慢指针永远指向尾节点，应该返回头节点
            return head; // 返回头节点，此时的头节点已经被慢指针覆盖
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}