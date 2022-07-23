package io.github.gcdd1993.java.study.springboot.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/7/22
 */
@Slf4j
public class OnceJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("我只会在 {} 执行一次", LocalDateTime.now());
    }
}
