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
@Mapper(config = MapperSpringConfig.class)
public interface PersonConverter1 extends Converter<PersonPo, PersonVo> {
}
