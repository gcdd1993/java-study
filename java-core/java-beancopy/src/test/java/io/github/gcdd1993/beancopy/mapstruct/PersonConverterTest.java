package io.github.gcdd1993.beancopy.mapstruct;

import com.github.jsonzou.jmockdata.JMockData;
import io.github.gcdd1993.beancopy.model.PersonDto;
import io.github.gcdd1993.beancopy.model.PersonPo;
import io.github.gcdd1993.beancopy.model.PersonVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

/**
 * @author gcdd1993
 * @date 2021/12/2
 * @since 1.0.0
 */
class PersonConverterTest {
    private PersonConverter personConverter = Mappers.getMapper(PersonConverter.class);
    private PersonPo po;

    @BeforeEach
    void setUp() {
        po = JMockData.mock(PersonPo.class);
        System.out.println(po);
    }

    @Test
    void po2Dto() {
        PersonDto dto = personConverter.po2Dto(po);
        System.out.println(dto);

        Assertions.assertEquals(dto.getId(), po.getId());
    }


    @Test
    void po2Vo() {
        PersonVo vo = personConverter.po2Vo(po);
        System.out.println(vo);

        Assertions.assertEquals(vo.getRealAge().intValue(), po.getRealAge());
    }
}