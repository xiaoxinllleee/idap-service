package org.cmms.modules.xyjlcx.xybg.xybgcx.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.util.DateUtils;
import org.cmms.modules.xyjlcx.xybg.xybgcx.service.ICreditReportQueryService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Administrator
 */
@Slf4j
@Api(tags = "信用报告查询")
@RestController
@RequestMapping("/creditreportqueryTy")
public class CreditReportQueryTyJobController implements Job {
    @Autowired
    private ICreditReportQueryService iCreditReportQueryService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(String.format("开始执行征信报告每日跑批任务-" + DateUtils.getTimestamp()));
        iCreditReportQueryService.initZx();
        log.info(String.format("征信报告每日跑批任务执行结束end-" + DateUtils.getTimestamp()));
    }
}
