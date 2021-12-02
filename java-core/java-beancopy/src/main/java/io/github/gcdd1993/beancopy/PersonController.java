package io.github.gcdd1993.beancopy;

import com.github.jsonzou.jmockdata.JMockData;
import io.github.gcdd1993.beancopy.mapstruct.PersonConverter;
import io.github.gcdd1993.beancopy.model.PersonPo;
import io.github.gcdd1993.beancopy.model.PersonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author gcdd1993
 * @date 2021/12/2
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonConverter personConverter;

    @GetMapping
    public List<PersonVo> list() {
        return IntStream.range(0, 20)
                .boxed()
                .map(__ -> JMockData.mock(PersonPo.class))
                .map(__ -> personConverter.po2Vo(__))
                .collect(Collectors.toUnmodifiableList());
    }


}
