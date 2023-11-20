package org.cmms.modules.ScheduledTask;

import lombok.extern.slf4j.Slf4j;
import org.cmms.common.util.DateUtil;
import org.cmms.modules.system.entity.SysExeinfoLog;
import org.cmms.modules.system.service.ISysExeinfoLogService;
import org.cmms.modules.util.EtlUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Slf4j
public class PkgDkfxHisController implements Job, Serializable {
    @Autowired
    private ISysExeinfoLogService iSysExeinfoLogService;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Long startTime = System.currentTimeMillis();

            Date date  = DateUtil.getDayBegin(DateUtil.getLastday_Month(new Date(), 0));
            Date today = DateUtil.getDayBegin(new Date());
            sdf = new SimpleDateFormat("yyyyMMdd");
            String tjyfyc = sdf.format(DateUtil.getFirstday_Month(date,0));
            SysExeinfoLog exeinfoLog = new SysExeinfoLog();
            exeinfoLog.setLogType(2);
            exeinfoLog.setLogContent("业务管理/存贷款分析/HIS表数据备份/定时任务/count_auto_his/params[tjyfyc]："+tjyfyc);
            exeinfoLog.setMethod("org.cmms.modules.ScheduledTask.PkgDkfxHisController.execute()");
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            exeinfoLog.setExeStartTime(sdf.format(startTime));

            boolean flag = false;
            if ("true".equals(sfdsjpt)) {
                if (date.compareTo(today) == 0){
                    HashMap<String, String> param = new HashMap<>();
                    param.put("tjyfyc", tjyfyc);
                    flag = EtlUtil.callEtl("count_auto_his", param, 30);
                    if (!flag) {
                        log.info("存储调度`count_auto_his`失败，请联系系统管理员查看！");
                    }
                    log.info("当前日期："+today+"为月末日期："+date+"，备份已完成：" + tjyfyc);
                } else {
                    log.info("当前日期："+today+"非月末日期："+date+"，跳过此次备份！");
                }
            }
            Long endTime = System.currentTimeMillis();
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            exeinfoLog.setExeEndTime(sdf.format(endTime));
            exeinfoLog.setCostTime(String.valueOf((endTime - startTime) / 1000));
            exeinfoLog.setExeStatus(String.valueOf(flag));
            iSysExeinfoLogService.save(exeinfoLog);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
