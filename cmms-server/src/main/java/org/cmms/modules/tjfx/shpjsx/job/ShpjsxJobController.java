package org.cmms.modules.tjfx.shpjsx.job;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.DateUtils;
import org.cmms.modules.tjfx.shpjsx.wg.service.ITjfxPjsxtjbbShService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author 龚辉
 * @date 2023/9/10 22:05 周日
 */
@Slf4j
@Api(tags = "商户评级信息每日跑批")
@RestController
public class ShpjsxJobController implements Job {
    @Autowired
    private ITjfxPjsxtjbbShService tjfxPjsxtjbbShService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(String.format("开始执行商户评级授信统计-" + DateUtils.getTimestamp()));
        tjfxPjsxtjbbShService.initData(DateUtil.format(new Date(),"yyyyMMdd"),"");
        log.info(String.format("商户评级授信统计结束end-" + DateUtils.getTimestamp()));
    }
}
