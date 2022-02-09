package io.github.gcdd1993.leetcode.editor.cn;

import java.util.Arrays;

public class Num204CountPrimes {
    public static void main(String[] args) {
        Solution solution = new Num204CountPrimes().new Solution();

        System.out.println(solution.countPrimes(10));
        System.out.println(solution.countPrimes(49979));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 排除法
         * <img src="https://img.gcdd.top/img/202202091334385.png"/>
         *
         * @param n
         * @return
         */
        public int countPrimes(int n) {
            if (n < 2) {
                return 0;
            }
            boolean[] isPrime = new boolean[n]; // 初始化数组，标记[0,n]的每个元素是否为素数
            // 初始化为true
            Arrays.fill(isPrime, true);
            isPrime[0] = false;
            isPrime[1] = false;

            for (int i = 2; i * i < n; i++) {
                if (isPrime[i]) {
                    // i 的倍数不可能为素数
                    // 起始位置存在冗余计算，可以从 i * i 开始
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = false;
                    }
                }
            }

            // 结果计数
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (isPrime[i]) {
                    count++;
                }
            }
            return count;

        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}