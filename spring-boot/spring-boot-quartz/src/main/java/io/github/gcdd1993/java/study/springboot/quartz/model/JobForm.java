package io.github.gcdd1993.java.study.springboot.quartz.model;

import lombok.Data;

import java.util.Map;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/7/22
 */
@Data
public class JobForm {
    private String jobName;
    private String jobGroupName;
    private String jobTime;
    private Map<String, ?> jobData;
}
