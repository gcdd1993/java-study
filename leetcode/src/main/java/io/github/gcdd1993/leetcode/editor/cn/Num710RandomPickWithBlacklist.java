package io.github.gcdd1993.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Num710RandomPickWithBlacklist {
    public static void main(String[] args) {
        Solution solution = new Num710RandomPickWithBlacklist().new Solution(4, new int[]{3, 1});

        for (int i = 0; i < 100; i++) {
            System.out.println(solution.pick());
        }

    }

    //    class Solution {
//
//        // 超出内存限制 [[1000000000,[640145908]]
//        private final List<Integer> value = new ArrayList<>(); // 存储 [0，n) 中的元素
//        private final Map<Integer, Integer> map = new HashMap<>(); // 映射 [0，n) 中的元素 索引，方便快速查找
//
//        private final Random random = new Random();
//
//        // 构造函数，输入参数
//        public Solution(int n, int[] blacklist) {
//            for (int i = 0; i < n; i++) {
//                value.add(i);
//                map.put(i, i);
//            }
//            // 处理黑名单
//            for (int blackValue : blacklist) {
//                remove(blackValue);
//            }
//        }
//
//        // 在区间 [0,N) 中等概率随机选取一个元素并返回
//        // 这个元素不能是 blacklist 中的元素
//        public int pick() {
//            // 因为黑名单元素在初始化时已经移除，所以只需要返回一个随机数即可
//            return value.get(random.nextInt(value.size()));
//        }
//
//        /**
//         * 移除黑名单元素
//         *
//         * @param blackValue 黑名单元素
//         */
//        private void remove(int blackValue) {
//            if (map.containsKey(blackValue)) {
//                // 1. 获取黑名单元素索引
//                Integer index = map.get(blackValue);
//                // 2. 将黑名单元素替换到队尾
//                Integer last = value.get(value.size() - 1); // 获取队尾元素
//                value.set(index, last);
//                map.put(last, index);
//                // 3. 移除队尾元素
//                value.remove(value.size() - 1);
//                map.remove(blackValue);
//            }
//        }
//    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 题目给出的N
        private final int n;
        // N - blacklist.length
        private final int sz;
        private final Random random = new Random();
        private final Map<Integer, Integer> map = new HashMap<>();

        // 构造函数，输入参数
        public Solution(int n, int[] blacklist) {
            this.n = n;
            this.sz = n - blacklist.length;

            // 将所有黑名单加入Map
            for (int b : blacklist) {
                // 这里赋值多少都可以
                // 目的仅仅是把键存进哈希表
                // 方便快速判断数字是否在黑名单内
                map.put(b, 0);
            }

            int last = n - 1; // 队尾索引
            for (int b : blacklist) {
                // 如果 blacklist 中的黑名单数字本身就存在区间 [sz, N) 中，那么就没必要在 mapping 中建立映射
                // 如果 b 已经在区间 [sz, N)
                // 可以直接忽略
                if (b >= sz) {
                    continue;
                }
                // 跳过所有黑名单元素
                while (map.containsKey(last)) {
                    last--;
                }

                // 将黑名单元素索引映射到合法数字
                map.put(b, last);
                last--;
            }

        }

        // 将黑名单中的数字都交换到区间[sz, N)中
        // 把[0,sz)占用的黑名单数字映射到正常数字
        // sz 是除去黑名单剩余元素长度， N是数组长度
        public int pick() {
            // 获取的元素长度为 n - 黑名单长度
            int index = random.nextInt(sz);
            // 命中黑名单索引
            if (map.containsKey(index)) {
                // 映射的是非黑名单数字
                return map.get(index);
            }
            // 不是黑名单，直接返回
            return index;
        }
    }

    /**
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(n, blacklist);
     * int param_1 = obj.pick();
     */
    //leetcode submit region end(Prohibit modification and deletion)

}