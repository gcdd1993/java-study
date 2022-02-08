package io.github.gcdd1993.leetcode.editor.cn;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Num870AdvantageShuffle {
    public static void main(String[] args) {
        Solution solution = new Num870AdvantageShuffle().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 田忌赛马算法
         *
         * @param nums1 需要重排序的数组
         * @param nums2 目标数组
         * @return
         */
        public int[] advantageCount(int[] nums1, int[] nums2) {
            int n = nums1.length;

            // 使用二叉堆存储，进行nums2的排序
            // 存储的机构为数组，索引0存储nums2的原位置索引，索引1存储nums2的值
            PriorityQueue<int[]> nums2MaxQueue = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);

            for (int i = 0; i < nums2.length; i++) {
                nums2MaxQueue.offer(new int[]{i, nums2[i]});
            }

            // 快速排序nums1
            Arrays.sort(nums1);

            // 使用双指针，降低时间复杂度
            // nums1[left]是最小值，nums1[right] 是最大值
            int left = 0, right = n - 1;
            int[] res = new int[n];

            while (!nums2MaxQueue.isEmpty()) {
                // 从二叉堆中取出最大值
                int[] poll = nums2MaxQueue.poll();
                int i = poll[0];
                int maxValue = poll[1];
                // 这里为什么要使用 > 而不是 >=？
                // 等于的时候，不一定能赢，但是可能比nums2的下一个元素大，所以等于的情况下，也要使用最小值替补
                if (nums1[right] > maxValue) {
                    // 如果最大值大于nums2的最大值，那么就用这个值就行
                    res[i] = nums1[right];
                    right--;
                } else {
                    // 如果比不过，那么就用最小值替补（送人头）
                    res[i] = nums1[left];
                    left++;
                }
            }
            return res;

        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}