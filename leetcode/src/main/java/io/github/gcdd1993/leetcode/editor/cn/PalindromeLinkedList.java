package io.github.gcdd1993.leetcode.editor.cn;

import io.github.gcdd1993.leetcode.editor.cn.model.ListNode;

public class PalindromeLinkedList {
    public static void main(String[] args) {
        Solution solution = new PalindromeLinkedList().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 1、先通过 双指针技巧 中的快慢指针来找到链表的中点
         * 2、如果fast指针没有指向null，说明链表长度为奇数，slow还要再前进一步
         * 3、从slow开始反转后面的链表，现在就可以开始比较回文串
         *
         * @param head
         * @return
         */
        public boolean isPalindrome(ListNode head) {
            // 通过 双指针技巧 中的快慢指针来找到链表的中点
            ListNode slow = head, fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            // 如果fast指针没有指向null，说明链表长度为奇数，slow还要再前进一步
            if (fast != null) {
                slow = slow.next;
            }

            // 从slow开始反转后面的链表
            ListNode left = head;
            ListNode right = reverse(slow);

            // 比较回文串
            while (right != null) {
                if (right.val != left.val) {
                    return false;
                }
                left = left.next;
                right = right.next;
            }
            return true;
        }

        /**
         * 反转node往后的节点
         */
        private ListNode reverse(ListNode head) {
            ListNode pre = null, cur = head;

            while (cur != null) {
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }

            return pre;
        }

//        public boolean isPalindrome(ListNode head) {
//            left = head;
//            return traverse(left);
//        }
//
//        // 左侧指针
//        private ListNode left;
//
//        /**
//         * 后序遍历
//         */
//        private boolean traverse(ListNode right) {
//            if (right == null) {
//                return true;
//            }
//
//            boolean res = traverse(right.next);
//            // 后序遍历代码
//            res = res && (right.val == left.val);
//            left = left.next;
//            return res;
//        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}