package io.github.gcdd1993.leetcode.editor.cn.model;

import java.util.List;

/**
 * @author gcdd1993
 * @since 2022/1/30
 */
public interface NestedInteger {
    // 如果其中存的是一个整数，则返回 true，否则返回 false
    public boolean isInteger();

    // 如果其中存的是一个整数，则返回这个整数，否则返回 null
    public Integer getInteger();

    // 如果其中存的是一个列表，则返回这个列表，否则返回 null
    public List<NestedInteger> getList();
}
