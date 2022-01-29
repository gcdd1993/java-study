package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.ListNode;

/**
 * 递归反转整个链表
 */
public class Num206ReverseLinkedList {
    public static void main(String[] args) {
        Solution solution = new Num206ReverseLinkedList().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     */
    class Solution {
        public ListNode reverseList(ListNode head) {
            // 如果链表只有一个节点的时候反转也是它自己，直接返回即可
            if (head == null || head.next == null) {
                return head;
            }
            ListNode last = reverseList(head.next);
            // <img src=”https://labuladong.gitee.io/algo/images/%e5%8f%8d%e8%bd%ac%e9%93%be%e8%a1%a8/4.jpg“/>
            head.next.next = head;
            // 当链表递归反转之后，新的头结点是 last，而之前的 head 变成了最后一个节点，别忘了链表的末尾要指向 null
            head.next = null;
            return last;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}