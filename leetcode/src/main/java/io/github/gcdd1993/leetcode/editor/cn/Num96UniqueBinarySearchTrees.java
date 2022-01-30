package io.github.gcdd1993.leetcode.editor.cn;

public class Num96UniqueBinarySearchTrees {
    public static void main(String[] args) {
        Solution solution = new Num96UniqueBinarySearchTrees().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 备忘录，解决结果重复问题，下标从0开始到n-1
        int[][] memo;

        public int numTrees(int n) {
            // 初始化备忘录
            // 为什么大小是 n+1？实际上是冗余了，下标0的没用上
            memo = new int[n][n];
            // 计算闭区间 [1, n] 组成的 BST 个数
            return count(1, n);
        }

        /**
         * 闭区间[lo, hi]的数字能组成 count(lo, hi)种 BST
         *
         * @param lo 低位
         * @param hi 高位
         * @return 数量
         */
        private int count(int lo, int hi) {
            // base case
            // 空区间，也就对应着空节点 null，虽然是空节点，但是也是一种情况
            if (lo > hi) {
                return 1;
            }

            // 查备忘录
            if (memo[lo - 1][hi - 1] > 0) {
                return memo[lo - 1][hi - 1];
            }

            int res = 0;
            for (int i = lo; i <= hi; i++) {
                //i的值作为根节点root
                int leftCount = count(lo, i - 1);
                int rightCount = count(i + 1, hi);
                // 左右子树的组合数乘积是BST的总数
                res += leftCount * rightCount;
            }
            // 将结果存入备忘录
            memo[lo - 1][hi - 1] = res;
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}