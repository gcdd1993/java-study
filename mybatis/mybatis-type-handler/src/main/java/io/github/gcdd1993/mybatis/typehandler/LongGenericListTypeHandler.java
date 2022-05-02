package io.github.gcdd1993.mybatis.typehandler;

import org.apache.ibatis.type.MappedTypes;

import java.util.List;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @since 2022/5/2
 */
@MappedTypes(List.class)
public class LongGenericListTypeHandler extends AbstractGenericListTypeHandler<Long> {
    @Override
    protected Long parse(String s) {
        return Long.parseLong(s);
    }
}
