package io.github.gcdd1993.leetcode.editor.cn;

import java.util.*;

public class Num380InsertDeleteGetrandomO1 {
    public static void main(String[] args) {
        RandomizedSet solution = new Num380InsertDeleteGetrandomO1().new RandomizedSet();

//        System.out.println(solution.insert(1));
//        System.out.println(solution.remove(2));
//        System.out.println(solution.insert(2));
//
//        System.out.println(solution.getRandom());
//
//        System.out.println(solution.remove(1));
//        System.out.println(solution.insert(2));
//        System.out.println(solution.getRandom());
        // ["RandomizedSet","insert","insert","remove","insert","remove","getRandom"]
        // [[],[0],[1],[0],[2],[1],[]]
//        System.out.println(solution.insert(0));
//        System.out.println(solution.insert(1));
//        System.out.println(solution.remove(0));
//        System.out.println(solution.insert(2));
//        System.out.println(solution.remove(1));
//
//        System.out.println(solution.getRandom());

        // "RandomizedSet","insert","insert","insert","insert","getRandom"
        // [],[1],[10],[20],[30],[]

        System.out.println(solution.insert(1));
        System.out.println(solution.insert(10));
        System.out.println(solution.insert(20));
        System.out.println(solution.insert(30));

        System.out.println(solution.getRandom());
        System.out.println(solution.getRandom());
        System.out.println(solution.getRandom());
        System.out.println(solution.getRandom());
        System.out.println(solution.getRandom());
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 如何O(1)时间等概率返回随机元素？
     * 底层用数组实现，且数组必须是紧凑的
     * <p>
     * 数组如何O(1)时间删除元素？
     * 从数组末尾删除元素，不需要数组拷贝，可以将待删除的元素替换到数组尾部，然后pop
     */
    class RandomizedSet {

        // 存储元素的数组
        private final List<Integer> value = new ArrayList<>();
        // 值 -> 索引
        private final Map<Integer, Integer> valueToIndex = new HashMap<>();
        private final Random random = new Random();

        public RandomizedSet() {
        }

        public boolean insert(int val) {
            // 已经存在该元素
            if (valueToIndex.containsKey(val)) {
                return false;
            }
            value.add(val);
            valueToIndex.put(val, value.size() - 1); // 添加的元素位置为队尾
            return true;
        }

        public boolean remove(int val) {
            // 不存在该元素
            if (!valueToIndex.containsKey(val)) {
                return false;
            }
            int i = valueToIndex.get(val); // 获取元素索引
            swap(i);

            valueToIndex.remove(val);

            return true;
        }

        public int getRandom() {
            // random [0, value.size() - 1]
            return value.get(random.nextInt(value.size()));
        }

        private int random(int upper) {
            return (int) (Math.random() * upper);
        }

        private void swap(int i) {
            Integer last = value.get(value.size() - 1);// 获取队尾元素
            value.set(i, last);
            // 删除队尾元素
            value.remove(value.size() - 1);
            valueToIndex.put(last, i); // 更新哈希表索引位置
        }

    }

    /**
     * Your RandomizedSet object will be instantiated and called as such:
     * RandomizedSet obj = new RandomizedSet();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */
    //leetcode submit region end(Prohibit modification and deletion)

}