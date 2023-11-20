package org.cmms.modules.quartz.service.impl;


import org.cmms.common.exception.JeecgBootException;
import org.cmms.modules.quartz.entity.QuartzLog;
import org.cmms.modules.quartz.listener.repeatActionListening;
import org.cmms.modules.quartz.mapper.SysQuartLogMapper;
import org.cmms.modules.quartz.service.ISysQuartLogService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: wdw
 * @Author: jeecg-boot
 * @Date: 2022-09-06
 * @Version: V1.0
 */
@Service
public class SysQuartLogServiceImpl extends ServiceImpl<SysQuartLogMapper, QuartzLog> implements ISysQuartLogService {

    @Autowired
    private Scheduler scheduler;


    @Override
    public void repeateExecuteByJobId (QuartzLog quartzLog) throws Exception {
        String jobClassName = quartzLog.getJobClassName();
        // 启动调度器

            JobKey jobKey = new JobKey(jobClassName + ".update");
            if (scheduler.checkExists(jobKey)) {
                scheduler.resumeJob(jobKey);
            }
            JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName + ".update").usingJobData("parameter", quartzLog.getId()).build();
            scheduler.getListenerManager().addJobListener(new repeatActionListening());
            scheduler.start();
            // 构建job信息
            SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).withRepeatCount(0);
            // 按新的cronExpression表达式构建一个新的trigger
            SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger().withIdentity(jobClassName).startAt(new Date()).withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, simpleTrigger);


    }

    private static Job getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (Job) class1.newInstance();
    }


}
