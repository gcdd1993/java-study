package io.github.gcdd1993.java.study.springboot.bizlog.logrecord;

import com.mzt.logapi.beans.Operator;
import com.mzt.logapi.service.IOperatorGetService;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/5/29
 */
@Component
public class OperatorGetServiceImpl implements IOperatorGetService {

    @Override
    public Operator getUser() {
        return new Operator("gcdd1993");
    }
}
