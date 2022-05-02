package io.github.gcdd1993.mybatis.typehandler;

import org.apache.ibatis.type.MappedTypes;

import java.util.List;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @since 2022/5/2
 */
@MappedTypes(List.class)
public class DoubleGenericListTypeHandler extends AbstractGenericListTypeHandler<Double> {
    @Override
    protected Double parse(String s) {
        return Double.parseDouble(s);
    }
}
