package io.github.gcdd1993.leetcode.editor.cn.commons;

/**
 * 差分数组工具类
 *
 * @author gcdd1993
 * @since 2022/1/31
 */
public class Difference {

    /**
     * 差分数组
     */
    private final int[] diff;

    /**
     * 输入一个初始数组，区间操作将在这个数组上进行
     *
     * <img src="https://cdn.jsdelivr.net/gh/gcdd1993/image-repo@master/img/202201311353677.png"/>
     *
     * @param nums 初始数组
     */
    public Difference(int[] nums) {
        assert nums.length > 0;
        diff = new int[nums.length];
        // 第一个元素存储的是原始数字
        diff[0] = nums[0];
        // 根据初始数组构造差分数组
        for (int i = 1; i < nums.length; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }
    }

    /**
     * 给闭区间 [i,j] 增加 val（可以是负数）
     *
     * <img src="https://cdn.jsdelivr.net/gh/gcdd1993/image-repo@master/img/202201311400336.png"/>
     *
     * @param i
     * @param j
     * @param val
     */
    public void increment(int i, int j, int val) {
        diff[i] += val;
        if (j + 1 < diff.length) {
            diff[j + 1] -= val;
        }
    }

    /**
     * 返回结果数组
     *
     * @return nums
     */
    public int[] result() {
        int[] res = new int[diff.length];
        // 根据差分数组换算原始数组
        res[0] = diff[0];
        for (int i = 1; i < diff.length; i++) {
            res[i] = res[i - 1] + diff[i];
        }
        return res;
    }

}
