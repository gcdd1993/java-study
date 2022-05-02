package io.github.gcdd1993.mybatis.typehandler;

import org.apache.ibatis.type.MappedTypes;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @since 2022/5/2
 */
@Deprecated(forRemoval = true) // 使用泛型，无效
@MappedTypes(List.class)
public class DefaultGenericListTypeHandler extends AbstractGenericListTypeHandler<Object> {

    private final Class<?> type;

    public DefaultGenericListTypeHandler(Class<?> type) {
        Assert.notNull(type, "Type argument cannot be null");
        this.type = type;
    }

    @Override
    protected Object parse(String s) {
        return type.cast(s);
    }
}
