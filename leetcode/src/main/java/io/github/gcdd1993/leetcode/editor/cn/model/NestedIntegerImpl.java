package io.github.gcdd1993.leetcode.editor.cn.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author gcdd1993
 * @since 2022/1/30
 */
@AllArgsConstructor
@NoArgsConstructor
public class NestedIntegerImpl implements NestedInteger {
    private Integer val;
    private List<NestedInteger> list;

    @Override
    public boolean isInteger() {
        return val != null;
    }

    @Override
    public Integer getInteger() {
        return val;
    }

    @Override
    public List<NestedInteger> getList() {
        return list;
    }
}
