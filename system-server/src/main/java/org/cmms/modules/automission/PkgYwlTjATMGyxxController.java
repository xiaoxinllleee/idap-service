package org.cmms.modules.automission;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Date;
import java.util.HashMap;

/**
 * @Description Mission_5 : 每日任务调度执行 - ATM柜员信息（PKG_YWL_TJ.P_AMTGYXX_INIT(to_char(p_tmpday,'yyyyMMdd'))）
 * @Author Penghr
 * @Date 2021年12月31日 11:43:41
 */
@Slf4j
@Api(tags="ATM柜员信息")
@RestController
@RequestMapping("/automission/atmgyxx")
public class PkgYwlTjATMGyxxController implements Job {
    @Autowired
    private IBigdataAutoexecLogService iBigdataAutoexecLogService;
    @Autowired
    private ISysDictService iSysDictService;
    @Autowired
    private RedisUtil redisUtil;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //当前系统日期
        Date today = new Date();
        //System.out.println("当前系统日期 today："+today);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        //每日调度日志记录
        BigdataAutoexecLog form = new BigdataAutoexecLog();
        form.setMissionName("mission_5 - ATM柜员信息（PKG_YWL_TJ.P_AMTGYXX_INIT）");
        form.setMissionClassName(PkgYwlTjATMGyxxController.class.getName());
        form.setExecTime(new Timestamp(System.currentTimeMillis()));
        HashMap<String, String> params = new HashMap<>();
        params.put("i_day", simpleDateFormat.format(today));
        //System.out.println("调度参数 params："+params);
        System.out.println("截至[ " + new Timestamp(System.currentTimeMillis()) + " ]，每日任务调度执行 - mission_5 - ATM柜员信息，开始调度！");
        boolean flag = EtlUtil.callEtl("count_ywltj_p_atmgyxx_init", params, 15);
        if (flag) {
            System.out.println("截至[ " + new Timestamp(System.currentTimeMillis()) + " ]，每日任务调度执行 - mission_5 - 待分配存款账号自动提取，调度成功！");
            form.setMissionSuccess(1);
        } else {
            System.out.println("截至[ " + new Timestamp(System.currentTimeMillis()) + " ]，每日任务调度执行 - mission_5 - 待分配存款账号自动提取，调度失败！");
            form.setMissionSuccess(0);
            form.setFailedEtlName("count_ywltj_p_atmgyxx_init");
            form.setReasonForFailure("ETL脚本[ count_ywltj_p_atmgyxx_init ]，调度执行失败！");
        }
        form.setCompleteTime(new Timestamp(System.currentTimeMillis()));
        iBigdataAutoexecLogService.save(form);
    }

}
