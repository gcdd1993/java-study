package io.github.gcdd1993.mybatis.typehandler;

import org.apache.ibatis.type.MappedTypes;

import java.util.List;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @since 2022/5/2
 */
@MappedTypes(List.class)
public class StringGenericListTypeHandler extends AbstractGenericListTypeHandler<String> {
    @Override
    protected String parse(String s) {
        return s;
    }
}
