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
import java.util.HashMap;

/**
 * @Description Mission_3 : 每日任务调度执行 - 待分配存款账号自动提取（PKG_YWGL_CKYW.P_DFPCKZH_INIT(to_char(p_tmpday,'yyyyMMdd'))）
 * @Author Penghr
 * @Date 2021年12月31日 11:10:22
 */
@Slf4j
@Api(tags="待分配存款账号自动提取")
@RestController
@RequestMapping("/automission/dfpckzh")
public class PkgYwglCkywController implements Job {
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
        int check = iBigdataAutoexecLogService.judgeExtract();
        String maxId = iBigdataAutoexecLogService.getMaxId();
        //每日调度日志记录
        BigdataAutoexecLog form = new BigdataAutoexecLog();
        form.setMissionName("mission_3 - 待分配存款账号自动提取（PKG_YWGL_CKYW.P_DFPCKZH_INIT）");
        form.setMissionClassName(PkgYwglCkywController.class.getName());
        form.setExecTime(new Timestamp(System.currentTimeMillis()));
        HashMap<String, String> parm = new HashMap<>();
        parm.put("ckzhglxx_max_glid",maxId);
        //System.out.println("调度参数 param："+param);
        System.out.println("截至[ " + new Timestamp(System.currentTimeMillis()) + " ]，每日任务调度执行 - mission_3 - 待分配存款账号自动提取，开始调度！");
        if (check != 0) {
            parm.put("etl_task","kiss.domain.application.ckjkpt.proc_ckjkpt_dfpckzh_init_one");
            // count_ckjkpt_dfpckzh_init_one
            boolean flag = EtlUtil.callEtl("ckjkpt_common_init", parm, 15);
            if (flag) {
                System.out.println("截至[ " + new Timestamp(System.currentTimeMillis()) + " ]，每日任务调度执行 - mission_3 - 待分配存款账号自动提取，调度成功！");
                form.setMissionSuccess(1);
            } else {
                System.out.println("截至[ " + new Timestamp(System.currentTimeMillis()) + " ]，每日任务调度执行 - mission_3 - 待分配存款账号自动提取，调度失败！");
                form.setMissionSuccess(0);
                form.setFailedEtlName("ckjkpt_common_init");
                form.setReasonForFailure("ETL脚本[ ckjkpt_common_init ]，调度执行失败！");
            }
        } else {
            parm.put("etl_task","kiss.domain.application.ckjkpt.proc_ckjkpt_dfpckzh_init_two");
            // count_ckjkpt_dfpckzh_init_two
            boolean flag = EtlUtil.callEtl("ckjkpt_common_init", parm, 15);
            if (flag) {
                System.out.println("截至[ " + new Timestamp(System.currentTimeMillis()) + " ]，每日任务调度执行 - mission_3 - 待分配存款账号自动提取，调度成功！");
                form.setMissionSuccess(1);
            } else {
                System.out.println("截至[ " + new Timestamp(System.currentTimeMillis()) + " ]，每日任务调度执行 - mission_3 - 待分配存款账号自动提取，调度失败！");
                form.setMissionSuccess(0);
                form.setFailedEtlName("ckjkpt_common_init");
                form.setReasonForFailure("ETL脚本[ ckjkpt_common_init ]，调度执行失败！");
            }
        }
        form.setCompleteTime(new Timestamp(System.currentTimeMillis()));
        iBigdataAutoexecLogService.save(form);
    }
}
