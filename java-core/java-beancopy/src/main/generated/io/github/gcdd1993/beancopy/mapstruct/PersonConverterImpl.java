package io.github.gcdd1993.beancopy.mapstruct;

import io.github.gcdd1993.beancopy.model.PersonDto;
import io.github.gcdd1993.beancopy.model.PersonPo;
import io.github.gcdd1993.beancopy.model.PersonVo;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-13T18:33:46+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (AdoptOpenJDK)"
)
@Component
public class PersonConverterImpl implements PersonConverter {

    @Override
    public PersonDto po2Dto(PersonPo po) {
        if ( po == null ) {
            return null;
        }

        PersonDto personDto = new PersonDto();

        personDto.setAge( po.getRealAge() );
        personDto.setId( po.getId() );
        personDto.setName( po.getName() );
        personDto.setCreateTime( po.getCreateTime() );
        personDto.setUpdateTime( po.getUpdateTime() );

        return personDto;
    }

    @Override
    public PersonVo po2Vo(PersonPo po) {
        if ( po == null ) {
            return null;
        }

        PersonVo personVo = new PersonVo();

        personVo.setId( po.getId() );
        personVo.setName( po.getName() );
        personVo.setCreateTime( po.getCreateTime() );
        personVo.setUpdateTime( po.getUpdateTime() );
        if ( po.getRealAge() != null ) {
            personVo.setRealAge( po.getRealAge().longValue() );
        }

        return personVo;
    }
}
