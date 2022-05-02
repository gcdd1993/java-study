package io.github.gcdd1993.mybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 将数组以英文逗号分隔，存入数据库
 *
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @since 2022/5/2
 */
public abstract class AbstractGenericListTypeHandler<E> extends BaseTypeHandler<List<E>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<E> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, listToString(parameter));
    }

    @Override
    public List<E> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return splitToList(rs.getString(columnName));
    }

    @Override
    public List<E> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return splitToList(rs.getString(columnIndex));
    }

    @Override
    public List<E> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return splitToList(cs.getString(columnIndex));
    }

    private String listToString(List<E> parameter) {
        if (!CollectionUtils.isEmpty(parameter)) {
            return parameter
                    .stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining(delimiter()))
                    ;
        } else {
            return null;
        }
    }

    private List<E> splitToList(String result) {
        if (StringUtils.hasText(result)) {
            return Stream.of(result.split(delimiter().toString()))
                    .map(this::parse)
                    .collect(Collectors.toList())
                    ;
        } else {
            return null;
        }
    }

    /**
     * 从字符串到泛型值
     *
     * @param s 字符串
     * @return {@link E}
     */
    protected abstract E parse(String s);

    /**
     * 分隔符
     *
     * @return 默认为{@code ,}
     */
    protected CharSequence delimiter() {
        return ",";
    }
}
