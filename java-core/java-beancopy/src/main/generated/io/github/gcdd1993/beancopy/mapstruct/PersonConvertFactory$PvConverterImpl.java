package io.github.gcdd1993.beancopy.mapstruct;

import io.github.gcdd1993.beancopy.mapstruct.PersonConvertFactory.PvConverter;
import io.github.gcdd1993.beancopy.model.PersonPo;
import io.github.gcdd1993.beancopy.model.PersonVo;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-10T17:18:02+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (AdoptOpenJDK)"
)
@Component
public class PersonConvertFactory$PvConverterImpl implements PvConverter {

    @Override
    public PersonVo convert(PersonPo source) {
        if ( source == null ) {
            return null;
        }

        PersonVo personVo = new PersonVo();

        personVo.setId( source.getId() );
        personVo.setName( source.getName() );
        personVo.setCreateTime( source.getCreateTime() );
        personVo.setUpdateTime( source.getUpdateTime() );
        if ( source.getRealAge() != null ) {
            personVo.setRealAge( source.getRealAge().longValue() );
        }

        return personVo;
    }
}
