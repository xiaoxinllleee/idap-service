package org.cmms.modules.automission;

import io.swagger.annotations.Api;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.RedisUtil;
import org.cmms.modules.automission.execlog.entity.BigdataAutoexecLog;
import org.cmms.modules.automission.execlog.service.IBigdataAutoexecLogService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.EtlUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * @Description Mission_1 : 每日任务调度执行 - 业务量自动统计（PKG_YWL_TJ.P_YSYWL_TJ_DAY(to_char(p_tmpday,'yyyyMMdd'),1,'')）
 * @Author Penghr
 * @Date 2021年12月30日 19:07:57
 */
@Slf4j
@Api(tags="业务量自动统计每日调度")
@RestController
@RequestMapping("/automission/ysywltj")
public class PkgYsywlTjController implements Job {
    @Autowired
    private IBigdataAutoexecLogService iBigdataAutoexecLogService;
    @Autowired
    private ISysDictService iSysDictService;
    @Autowired
    private RedisUtil redisUtil;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //当前系统日期
        Date today = new Date();
        //System.out.println("当前系统日期 today："+today);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.format(today);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);//设置当前时间
        calendar.add(Calendar.DAY_OF_MONTH, -2);//加减day时间
        String nowDate = simpleDateFormat.format(calendar.getTime());
        //System.out.println("当前系统日期 nowDate："+nowDate);
        HashMap<String, String> param = new HashMap<>();
        param.put("i_day", nowDate.replace("-",""));
        //P90050-业务量统计时是否统计ATM业务笔数(0 是 1 否)
        String paramValue = iBigdataAutoexecLogService.getAssessParamValue("P90050");
        param.put("p_atm_ywbs", paramValue);
        String ltn1 = iBigdataAutoexecLogService.conversionConfigInfo(simpleDateFormat.parse(nowDate));
        param.put("lt_n1", ltn1);
        param.put("i_zzbz", "");
        //System.out.println("调度参数 param："+param);

        //每日调度日志记录
        BigdataAutoexecLog form = new BigdataAutoexecLog();
        form.setMissionName("mission_1 - 业务量自动统计（PKG_YWL_TJ.P_YSYWL_TJ_DAY）");
        form.setMissionClassName(PkgYsywlTjController.class.getName());
        form.setExecTime(new Timestamp(System.currentTimeMillis()));
        System.out.println("截至[ " + new Timestamp(System.currentTimeMillis()) + " ]，每日任务调度执行 - mission_1 - 业务量自动统计，开始调度！");
        //若统计月份为当前月份且`i_isreconfig`为`1`，则重新配置当月的折算信息
        param.put("etl_task","kiss.domain.application.cdkyw.proc_business_volume_ysywl_reconfig");
        // count_business_volume_ysywl_reconfig
        boolean flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
        if (flag) {
            System.out.println("截至[ " + new Timestamp(System.currentTimeMillis()) + " ]，每日任务调度执行 - mission_1 - 业务量自动统计，调度成功！");
            form.setMissionSuccess(1);
        } else {
            form.setMissionSuccess(0);
            form.setFailedEtlName("cdkyw_common_init");
            form.setReasonForFailure("ETL脚本[ cdkyw_common_init ]，调度执行失败！");
            System.out.println("截至[ " + new Timestamp(System.currentTimeMillis()) + " ]，每日任务调度执行 - mission_1 - 业务量自动统计，调度失败！");
        }
        form.setCompleteTime(new Timestamp(System.currentTimeMillis()));
        iBigdataAutoexecLogService.save(form);
    }
}
