package org.cmms.modules.quartz.listener;

import org.cmms.common.exception.JeecgBootException;
import org.cmms.common.util.SpringContextUtils;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.quartz.constant.QuartzEnum;
import org.cmms.modules.quartz.entity.QuartzLog;
import org.cmms.modules.quartz.mapper.SysQuartLogMapper;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class repeatActionListening implements JobListener {

    @Autowired
    private Scheduler scheduler;

    private ThreadLocal<QuartzLog> threadLocal = new ThreadLocal<>();

    @Override
    public String getName() {
        return "repeatActionListening";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        JobDetail jobDetail = context.getJobDetail();
        QuartzLog quartzLog1 = new QuartzLog();
        String id = String.valueOf(jobDetail.getJobDataMap().get("parameter"));
        if (StringUtils.isNotEmpty(id)) {
            quartzLog1.setStartTime(new Date());
            quartzLog1.setId(id);
            quartzLog1.setStatus(QuartzEnum.QuartExecution.getKey());
            quartzLog1.setNextFireTime(new Date());
            System.out.println("-----开始执行----");
            threadLocal.set(quartzLog1);
        }

    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {

    }



    private void schedulerDelete(String jobClassName) {
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName));
            scheduler.deleteJob(JobKey.jobKey(jobClassName));
        } catch (Exception e) {

            throw new JeecgBootException("删除定时任务失败");
        }
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        JobDetail jobDetail = context.getJobDetail();
        Scheduler scheduler = context.getScheduler();
        SysQuartLogMapper sysQuartLogMapper = SpringContextUtils.getBean(SysQuartLogMapper.class);
        QuartzLog quartzLog1 = threadLocal.get();
        threadLocal.remove();
        if (StringUtils.isNotBlank(quartzLog1.getId())) {
            if (jobException != null) {
                quartzLog1.setEndTime(new Date());
                quartzLog1.setInfo(jobException.getMessage());
                quartzLog1.setStatus(QuartzEnum.QuartExecutionFail.getKey());
                sysQuartLogMapper.updateById(quartzLog1);
                System.out.println("----------执行出现异常-----");
            } else {
                quartzLog1.setEndTime(new Date());
                quartzLog1.setInfo(QuartzEnum.QuartExecutionSuccess.getValue());
                quartzLog1.setStatus(QuartzEnum.QuartExecutionSuccess.getKey());
                sysQuartLogMapper.updateById(quartzLog1);
                System.out.println("----------执行成功-----");

            }
        }
        if (jobDetail.getKey() != null) {
            try {
                scheduler.deleteJob(jobDetail.getKey());
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }


    }
}
