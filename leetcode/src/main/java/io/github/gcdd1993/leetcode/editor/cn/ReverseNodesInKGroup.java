package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.ListNode;

public class ReverseNodesInKGroup {
    public static void main(String[] args) {
        Solution solution = new ReverseNodesInKGroup().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null) {
                return null;
            }
            // 区间 [a, b) 包含 k 个待反转元素
            ListNode a, b;
            a = b = head;
            for (int i = 0; i < k; i++) {
                // 不足 k 个，不需要反转，base case
                if (b == null) {
                    return head;
                }
                b = b.next;
            }
            // 反转前 k 个元素
            ListNode newHead = reverse(a, b);
            // 递归反转后续链表并连接起来
            a.next = reverseKGroup(b, k);
            return newHead;
        }

        /**
         * 反转区间 [a, b) 的元素，注意是左闭右开
         */
        private ListNode reverse(ListNode a, ListNode b) {
            // 前一个节点，当前节点，后一个节点
            ListNode pre, cur, nxt;
            pre = null;
            cur = nxt = a;
            while (cur != b) {
                nxt = cur.next;
                // 逐个结点反转
                cur.next = pre;
                // 更新指针位置
                pre = cur;
                cur = nxt;
            }
            // 返回反转后的头结点
            return pre;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}