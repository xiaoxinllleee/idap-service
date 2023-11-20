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
 * @Description Mission_2 : 每日任务调度执行 - 自动针对机构、全行进行科目号月余额统计（PKG_IMP_TJ_KMZZ.P_MODM_TJBS_KMYE_JG.P_MODM_TJBS_KMYE(to_char(p_tmpday,'yyyyMMdd'))）
 * @Author Penghr
 * @Date 2021年12月31日 14:22:20
 */
@Slf4j
@Api(tags="自动针对机构、全行进行科目号月余额统计")
@RestController
@RequestMapping("/automission/kmyetj")
public class PkgImpTjKmzzController implements Job {
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");//每日调度日志记录
        BigdataAutoexecLog form = new BigdataAutoexecLog();
        form.setMissionName("Mission_2 - 自动针对机构、全行进行科目号月余额统计（PKG_IMP_TJ_KMZZ.P_MODM_TJBS_KMYE_JG.P_MODM_TJBS_KMYE）");
        form.setMissionClassName(PkgImpTjKmzzController.class.getName());
        form.setExecTime(new Timestamp(System.currentTimeMillis()));
        HashMap<String, String> params = new HashMap<>();
        params.put("i_day", simpleDateFormat.format(today));
        params.put("i_tjlx", "MM");
        params.put("lv_modm_tablename", "MODM_TJBS_KMYE_JG");
        params.put("lv_dmpm_tablename", "ZMTGLSGLSBUSINESSINFO"+simpleDateFormat.format(today).substring(2,6));
        //System.out.println("调度参数 params："+params);
        System.out.println("截至[ " + new Timestamp(System.currentTimeMillis()) + " ]，每日任务调度执行 - mission_2 - 自动针对机构、全行进行科目号月余额统计，开始调度！");
        boolean flag = EtlUtil.callEtl("count_kmzz_p_modm_tjbs_kmye_jg", params, 15);
        if (flag) {
            System.out.println("截至[ " + new Timestamp(System.currentTimeMillis()) + " ]，每日任务调度执行 - mission_2 - 自动针对机构、全行进行科目号月余额统计，调度成功！");
            form.setMissionSuccess(1);
        } else {
            System.out.println("截至[ " + new Timestamp(System.currentTimeMillis()) + " ]，每日任务调度执行 - mission_2 - 自动针对机构、全行进行科目号月余额统计，调度失败！");
            form.setMissionSuccess(0);
            form.setFailedEtlName("count_kmzz_p_modm_tjbs_kmye_jg");
            form.setReasonForFailure("ETL脚本[ count_kmzz_p_modm_tjbs_kmye_jg ]，调度执行失败！");
        }
        form.setCompleteTime(new Timestamp(System.currentTimeMillis()));
        iBigdataAutoexecLogService.save(form);
    }
}
