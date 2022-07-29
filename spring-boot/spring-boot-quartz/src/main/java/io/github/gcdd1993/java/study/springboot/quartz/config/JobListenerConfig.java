package io.github.gcdd1993.java.study.springboot.quartz.config;

import io.github.gcdd1993.java.study.springboot.quartz.job.RunLogJobListenerImpl;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/7/29
 */
@Component
public class JobListenerConfig implements ApplicationRunner {

    @Resource
    private SchedulerFactoryBean schedulerFactoryBean;

    @Resource
    private RunLogJobListenerImpl runLogJobListener;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        schedulerFactoryBean.getScheduler()
                .getListenerManager()
                .addJobListener(runLogJobListener);
    }
}
