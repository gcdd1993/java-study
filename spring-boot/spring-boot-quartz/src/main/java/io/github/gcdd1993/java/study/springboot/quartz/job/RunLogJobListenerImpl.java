package io.github.gcdd1993.java.study.springboot.quartz.job;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.gcdd1993.java.study.springboot.quartz.mapper.JobRunLogMapper;
import io.github.gcdd1993.java.study.springboot.quartz.model.JobRunLogPO;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/7/29
 */
@Component
public class RunLogJobListenerImpl extends JobListenerSupport {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Resource
    private JobRunLogMapper jobRunLogMapper;

    @Override
    public String getName() {
        return "任务执行日志";
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        try {
            JobDetail jobDetail = context.getJobDetail();
            JobRunLogPO po = new JobRunLogPO();
            po.setJobClass(jobDetail.getJobClass().getName());
            po.setJobName(jobDetail.getKey().getName());
            po.setJobGroup(jobDetail.getKey().getGroup());
            po.setJobDescription(jobDetail.getDescription());
            po.setJobDataMap(OBJECT_MAPPER.writeValueAsString(jobDetail.getJobDataMap()));
            if (jobException != null) {
                po.setRunResult(false);
                po.setErrMsg(jobException.getMessage());
            } else {
                po.setRunResult(true);
            }
            po.setRunTime(System.currentTimeMillis());
            jobRunLogMapper.insert(po);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
