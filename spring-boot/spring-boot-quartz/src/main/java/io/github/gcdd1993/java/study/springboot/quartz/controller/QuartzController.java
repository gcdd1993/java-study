package io.github.gcdd1993.java.study.springboot.quartz.controller;

import io.github.gcdd1993.java.study.springboot.quartz.job.HelloJob;
import io.github.gcdd1993.java.study.springboot.quartz.job.OnceJob;
import io.github.gcdd1993.java.study.springboot.quartz.model.JobForm;
import io.github.gcdd1993.java.study.springboot.quartz.quartz.QuartzService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * QuartZ任务管理
 *
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/7/22
 */
@RestController
@RequestMapping("/api/quartz")
public class QuartzController {

    @Resource
    private QuartzService quartzService;

    /**
     * 新建任务
     */
    @PostMapping
    void addJob(@RequestBody JobForm form) {
        quartzService.addJob(HelloJob.class, form.getJobName(), form.getJobGroupName(), form.getJobTime(), form.getJobData());
    }

    /**
     * 按照时间添加任务
     *
     * @param time 运行时间
     */
    @PostMapping("/addByTime")
    void addJobByTime(@RequestBody JobForm form,
                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant time) {
        quartzService.addJobByTime(OnceJob.class, form.getJobName(), form.getJobGroupName(), form.getJobData(), time);
    }

    /**
     * 获取所有计划中的任务列表
     *
     * @return 任务列表
     */
    @GetMapping("/queryAllJob")
    List<Map<String, Object>> queryAllJob() {
        return quartzService.queryAllJob();
    }

    /**
     * 删除任务
     *
     * @param jobName      任务名称
     * @param jobGroupName 任务组名
     */
    @DeleteMapping
    void deleteJob(@RequestParam String jobName, @RequestParam String jobGroupName) {
        quartzService.deleteJob(jobName, jobGroupName);
    }

}
