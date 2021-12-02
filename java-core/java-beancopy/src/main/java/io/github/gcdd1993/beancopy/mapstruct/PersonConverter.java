package io.github.gcdd1993.beancopy.mapstruct;

import io.github.gcdd1993.beancopy.model.PersonDto;
import io.github.gcdd1993.beancopy.model.PersonPo;
import io.github.gcdd1993.beancopy.model.PersonVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author gcdd1993
 * @date 2021/12/2
 * @since 1.0.0
 */
@Mapper(
        componentModel = "spring"
)
public interface PersonConverter {

    @Mapping(source = "realAge", target = "age")
    PersonDto po2Dto(PersonPo po);

    PersonVo po2Vo(PersonPo po);

}
