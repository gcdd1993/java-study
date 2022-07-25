package io.github.gcdd1993.java.study.springboot.quartz.job;

import io.github.gcdd1993.java.study.springboot.quartz.service.HelloService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/7/25
 */
public class InjectJob implements Job {

    @Resource
    private HelloService helloService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        helloService.sayHello();
    }
}
