package io.github.gcdd1993.beancopy.mapstruct;

import io.github.gcdd1993.beancopy.mapstruct.PersonConvertFactory.VpConverter;
import io.github.gcdd1993.beancopy.model.PersonPo;
import io.github.gcdd1993.beancopy.model.PersonVo;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-27T19:59:09+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (AdoptOpenJDK)"
)
@Component
public class PersonConvertFactory$VpConverterImpl implements VpConverter {

    @Override
    public PersonPo convert(PersonVo source) {
        if ( source == null ) {
            return null;
        }

        PersonPo personPo = new PersonPo();

        personPo.setId( source.getId() );
        personPo.setName( source.getName() );
        personPo.setCreateTime( source.getCreateTime() );
        personPo.setUpdateTime( source.getUpdateTime() );
        if ( source.getRealAge() != null ) {
            personPo.setRealAge( source.getRealAge().intValue() );
        }

        return personPo;
    }
}
