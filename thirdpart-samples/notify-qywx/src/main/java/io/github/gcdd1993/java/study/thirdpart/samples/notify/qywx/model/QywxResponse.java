package io.github.gcdd1993.java.study.thirdpart.samples.notify.qywx.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/7/28
 */
@NoArgsConstructor
@Data
public class QywxResponse {
    private Integer errcode;
    private String errmsg;
}
