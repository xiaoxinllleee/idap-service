package org.cmms.config;

import org.cmms.modules.quartz.listener.JobActionListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * quartz定时任务配置
 */
@Component
public class QuartzConfig {
    @Autowired
    private Scheduler scheduler;
    /**
     * 调度工厂
     * @return
     */
    @PostConstruct
    public void addSchedulerListener(){
        System.out.println("---------------addSchedulerListener---------------");
        try {
            scheduler.getListenerManager().addJobListener(new JobActionListener());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
