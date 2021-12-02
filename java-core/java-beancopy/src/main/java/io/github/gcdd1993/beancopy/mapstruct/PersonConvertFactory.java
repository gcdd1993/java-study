package io.github.gcdd1993.beancopy.mapstruct;

import io.github.gcdd1993.beancopy.model.PersonPo;
import io.github.gcdd1993.beancopy.model.PersonVo;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

/**
 * @author gcdd1993
 * @date 2021/12/2
 * @since 1.0.0
 */
public interface PersonConvertFactory/*<P, V, D>*/ { // 无法使用泛型定义，所以每个转换都要写一个Converter...

    @Mapper(config = MapperSpringConfig.class)
    interface PdConverter extends Converter<PersonPo, PersonVo> {
    }

    @Mapper(config = MapperSpringConfig.class)
    interface PvConverter extends Converter<PersonPo, PersonVo> {
    }

    @Mapper(config = MapperSpringConfig.class)
    interface VpConverter extends Converter<PersonVo, PersonPo> {
    }

}
