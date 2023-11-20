package org.cmms.modules.tjfx.birthdayreminder.task;

import lombok.extern.slf4j.Slf4j;
import org.cmms.modules.tjfx.birthdayreminder.service.IsrtxService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class srtxTask implements Job {

    @Autowired
    private IsrtxService isrtxService;


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("----------------开始执行生日提醒数据初始化存储过程定时任务数据同步-------------");
        isrtxService.initDataBySheduler();
        log.info("----------------开始执行生日提醒数据初始化存储过程定时任务数据同步完成-------------");

    }
}
