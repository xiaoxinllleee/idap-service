package org.cmms.modules.tjfx.qhywjd.job;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.util.DateUtils;
import org.cmms.modules.tjfx.qhywjd.qhckqk.service.ITjfxQhckqkService;
import org.cmms.modules.tjfx.qhywjd.qhdkqk.service.ITjfxQhdkqkService;
import org.cmms.modules.util.DateUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author 龚辉
 * @date 2023/9/8 10:02 周五
 */
@Slf4j
@Api(tags = "存贷款业务信息统计-定时任务")
public class QhywjdJobController implements Job {
    @Autowired
    private ITjfxQhckqkService tjfxQhckqkService;

    @Autowired
    private ITjfxQhdkqkService tjfxQhdkqkService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(String.format("开始执行存贷款业务信息统计-" + DateUtils.getTimestamp()));
        Date ckDate=tjfxQhckqkService.getMaxDate();
        tjfxQhckqkService.initData(DateUtil.dateToString(ckDate,"yyyy-MM-dd").replace("-",""),"");
        Date dkDate=tjfxQhdkqkService.getMaxDate();
        tjfxQhdkqkService.initData(DateUtil.dateToString(dkDate,"yyyy-MM-dd").replace("-",""),"");
        log.info(String.format("存贷款业务信息结束end-" + DateUtils.getTimestamp()));
    }
}
