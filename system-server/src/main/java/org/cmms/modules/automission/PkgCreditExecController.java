package org.cmms.modules.automission;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.util.RedisUtil;
import org.cmms.modules.automission.execlog.entity.BigdataAutoexecLog;
import org.cmms.modules.automission.execlog.service.IBigdataAutoexecLogService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.EtlUtilOld;
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
 * @Description Mission_6 : 每日任务调度执行 - 内部信用报告（PKG_CREDIT.P_AUTO_EXEC_DAY）
 * @Author Penghr
 * @Date 2021年12月31日 14:52:13
 */
@Slf4j
@Api(tags="内部信用报告")
@RestController
@RequestMapping("/automission/credit")
public class PkgCreditExecController implements Job {
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
        BigdataAutoexecLog form = new BigdataAutoexecLog();
        form.setMissionName("Mission_6 - 内部信用报告（PKG_CREDIT.P_AUTO_EXEC_DAY）");
        form.setMissionClassName(PkgCreditExecController.class.getName());
        form.setExecTime(new Timestamp(System.currentTimeMillis()));
        HashMap<String, String> params = new HashMap<>();
        params.put("etl_task","kiss.domain.application.zx.proc_credit_bwdksjmx");
        //存储调度_1：P_BWDKSJMX
        // count_credit_bwdksjmx
        boolean flag = EtlUtil.callEtl("zx_common_init", params, 20);
        if (!flag) {
            form.setMissionSuccess(0);
            form.setFailedEtlName("zx_common_init");
            form.setReasonForFailure("ETL脚本[ zx_common_init ]，调度执行失败！");
            System.out.println("存储调度`zx_common_init`失败，请联系系统管理员查看！");
        }
        //存储调度_2：P_SBGXMX
        if (flag) {
            System.out.println("存储调度-1 ==== 结束调度 ==== 存储调度-2 ==== 开始调度 ====");
            params = new HashMap<>();
            params.put("etl_task","kiss.domain.application.zx.proc_credit_sbgxmx");
            // count_credit_sbgxmx
            flag = EtlUtil.callEtl("zx_common_init", params, 20);
            if (!flag) {
                form.setMissionSuccess(0);
                form.setFailedEtlName("zx_common_init");
                form.setReasonForFailure("ETL脚本[ zx_common_init ]，调度执行失败！");
                System.out.println("存储调度`zx_common_init`失败，请联系系统管理员查看！");
            }
        }
        //存储调度_3：P_DKKHGLRGL
        if (flag) {
            System.out.println("存储调度-2 ==== 结束调度 ==== 存储调度-3 ==== 开始调度 ====");
            params = new HashMap<>();
            params.put("etl_task","kiss.domain.application.zx.proc_credit_dkkhglrgl");
            // count_credit_dkkhglrgl
            flag = EtlUtil.callEtl("zx_common_init", params, 20);
            if (!flag) {
                form.setMissionSuccess(0);
                form.setFailedEtlName("zx_common_init");
                form.setReasonForFailure("ETL脚本[ zx_common_init ]，调度执行失败！");
                System.out.println("存储调度`zx_common_init`失败，请联系系统管理员查看！");
            }
        }
        //存储调度_4：P_WGLRTQ
        if (flag) {
            System.out.println("存储调度-3 ==== 结束调度 ==== 存储调度-4 ==== 开始调度 ====");
            params = new HashMap<>();
            params.put("etl_task","kiss.domain.application.zx.proc_credit_wglrtq");
            // count_credit_wglrtq
            flag = EtlUtil.callEtl("zx_common_init", params, 20);
            if (!flag) {
                form.setMissionSuccess(0);
                form.setFailedEtlName("zx_common_init");
                form.setReasonForFailure("ETL脚本[ zx_common_init ]，调度执行失败！");
                System.out.println("存储调度`zx_common_init`失败，请联系系统管理员查看！");
            }
        }
        //存储调度_5：P_HMDTQ
        if (flag) {
            System.out.println("存储调度-4 ==== 结束调度 ==== 存储调度-5 ==== 开始调度 ====");
            params = new HashMap<>();
            params.put("etl_task","kiss.domain.application.zx.proc_credit_hmdtq");
            // count_credit_hmdtq
            flag = EtlUtil.callEtl("zx_common_init", params, 20);
            if (!flag) {
                form.setMissionSuccess(0);
                form.setFailedEtlName("zx_common_init");
                form.setReasonForFailure("ETL脚本[ zx_common_init ]，调度执行失败！");
                System.out.println("存储调度`zx_common_init`失败，请联系系统管理员查看！");
            }
        }
        //存储调度_6：P_HMDMC
        if (flag) {
            System.out.println("存储调度-5 ==== 结束调度 ==== 存储调度-6 ==== 开始调度 ====");
            params = new HashMap<>();
            params.put("etl_task","kiss.domain.application.zx.proc_credit_hmdmc");
            // count_credit_hmdmc
            flag = EtlUtil.callEtl("zx_common_init", params, 20);
            if (!flag) {
                form.setMissionSuccess(0);
                form.setFailedEtlName("zx_common_init");
                form.setReasonForFailure("ETL脚本[ zx_common_init ]，调度执行失败！");
                System.out.println("存储调度`zx_common_init`失败，请联系系统管理员查看！");
            }
        }
        //存储调度_7：P_SSGLYEGX
        if (flag) {
            System.out.println("存储调度-6 ==== 结束调度 ==== 存储调度-7 ==== 开始调度 ====");
            params = new HashMap<>();
            params.put("etl_task","kiss.domain.application.zx.proc_credit_ssglyegx");
            // count_credit_ssglyegx
            flag = EtlUtil.callEtl("zx_common_init", params, 20);
            if (!flag) {
                form.setMissionSuccess(0);
                form.setFailedEtlName("zx_common_init");
                form.setReasonForFailure("ETL脚本[ zx_common_init ]，调度执行失败！");
                System.out.println("存储调度`zx_common_init`失败，请联系系统管理员查看！");
            }
        }
        //存储调度_8：P_JCDK
        if (flag) {
            System.out.println("存储调度-7 ==== 结束调度 ==== 存储调度-8 ==== 开始调度 ====");
            params = new HashMap<>();
            params.put("etl_task","kiss.domain.application.zx.proc_credit_jcdk");
            // count_credit_jcdk
            flag = EtlUtil.callEtl("zx_common_init", params, 20);
            if (!flag) {
                form.setMissionSuccess(0);
                form.setFailedEtlName("zx_common_init");
                form.setReasonForFailure("ETL脚本[ zx_common_init ]，调度执行失败！");
                System.out.println("存储调度`zx_common_init`失败，请联系系统管理员查看！");
            }
        }
        if (flag) {
            System.out.println("截至[ " + new Timestamp(System.currentTimeMillis()) + " ]，每日任务调度执行 - Mission_6 - 内部信用报告，调度成功！");
            form.setMissionSuccess(1);
        } else {
            System.out.println("截至[ " + new Timestamp(System.currentTimeMillis()) + " ]，每日任务调度执行 - Mission_6 - 内部信用报告，调度失败！");
        }
        form.setCompleteTime(new Timestamp(System.currentTimeMillis()));
        iBigdataAutoexecLogService.save(form);
    }
}
