package org.cmms.modules.automission;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.RedisUtil;
import org.cmms.modules.automission.execlog.entity.BigdataAutoexecLog;
import org.cmms.modules.automission.execlog.service.IBigdataAutoexecLogService;
import org.cmms.modules.automission.execlog.vo.ErpBasWyxcsszVO;
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
 * @Description Mission_4 : 每日任务调度执行 - （PKG_DKFX_KHJLDKTJ.P_AUTO_EXEC(null, to_char(p_tmpday,'yyyyMMdd'), system)）
 * @Author Penghr
 * @Date 2021年12月31日 09:25:27
 */
@Slf4j
@Api(tags="客户经理贷款统计")
@RestController
@RequestMapping("/automission/khjldktj")
public class PkgDkfxKhjlDktjController implements Job {
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
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
        //当前系统日期
        Date today = new Date();
        //System.out.println("当前系统日期 today："+today);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String jgdm  = "";
        String tjyf  = simpleDateFormat.format(today);
        String lrczy = "system";
        HashMap<String, String> param = new HashMap<>();
        param.put("i_jgdm", jgdm);
        param.put("i_tjyf", tjyf);
        param.put("ls_lrczy", lrczy);
        //System.out.println("调度参数 param："+param);

        //每日调度日志记录
        BigdataAutoexecLog form = new BigdataAutoexecLog();
        form.setMissionName("Mission_4 - 客户经理贷款统计（PKG_DKFX_KHJLDKTJ.P_AUTO_EXEC）");
        form.setMissionClassName(PkgDkfxKhjlDktjController.class.getName());
        form.setExecTime(new Timestamp(System.currentTimeMillis()));
        System.out.println("截至[ " + new Timestamp(System.currentTimeMillis()) + " ]，每日任务调度执行 - Mission_4 - 客户经理贷款统计，开始调度！");
        param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_n_temp_dkyeb");
        // count_cdkfx_p_n_temp_dkyeb
        boolean flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
        if (!flag) {
            form.setMissionSuccess(0);
            form.setFailedEtlName("cdkyw_common_init");
            form.setReasonForFailure("ETL脚本[ cdkyw_common_init ]，调度执行失败！");
            System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
        }
        //存储调度-2：P_N_DKYEB - P_N_TEMP_DKYEBMXQKTJ - P_MODM_DKYEB_UPZRR - proc_cdkfx_p_modm_dkyeb_upzrr
        if (flag) {
            System.out.println("存储调度-1 ==== 结束调度 ==== 存储调度-2 ==== 开始调度 ====");
            param = new HashMap<>();
            param.put("i_tjyf", tjyf);
            param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_modm_dkyeb_upzrr");
            // count_cdkfx_p_modm_dkyeb_upzrr
            flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
            if (!flag) {
                form.setMissionSuccess(0);
                form.setFailedEtlName("cdkyw_common_init");
                form.setReasonForFailure("ETL脚本[ cdkyw_common_init ]，调度执行失败！");
                System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
            }
        }
        //存储调度-3：P_N_DKYEB - P_N_TEMP_DKYEBMXQKTJ - proc_cdkfx_p_n_temp_dkyebmxqktj
        if (flag) {
            System.out.println("存储调度-2 ==== 结束调度 ==== 存储调度-3 ==== 开始调度 ====");
            param = new HashMap<>();
            param.put("i_tjyf", tjyf);
            param.put("i_jgdm", jgdm);
            param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_n_temp_dkyebmxqktj");
            // count_cdkfx_p_n_temp_dkyebmxqktj
            flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
            if (!flag) {
                form.setMissionSuccess(0);
                form.setFailedEtlName("cdkyw_common_init");
                form.setReasonForFailure("ETL脚本[ cdkyw_common_init ]，调度执行失败！");
                System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
            }
        }
        //存储调度-4：P_N_DKYEB - P_N_TEMP_DKYEBMX - proc_cdkfx_p_n_temp_dkyebmx
        if (flag) {
            System.out.println("存储调度-3 ==== 结束调度 ==== 存储调度-4 ==== 开始调度 ====");
            param = new HashMap<>();
            param.put("i_tjyf", tjyf);
            param.put("i_jgdm", jgdm);
            param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_n_temp_dkyebmx");
            // count_cdkfx_p_n_temp_dkyebmx
            flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
            if (!flag) {
                form.setMissionSuccess(0);
                form.setFailedEtlName("cdkyw_common_init");
                form.setReasonForFailure("ETL脚本[ cdkyw_common_init ]，调度执行失败！");
                System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
            }
        }
        //存储调度-5：P_N_DKYEB - P_N_DKYEB_XZBLDK - proc_ywgl_cdkfx_n_dkyeb_xzbldk
        if (flag) {
            System.out.println("存储调度-4 ==== 结束调度 ==== 存储调度-5 ==== 开始调度 ====");
            param = new HashMap<>();
            param.put("i_tjyf", tjyf);
            param.put("i_jgdm", jgdm);
            param.put("etl_task","kiss.domain.application.cdkyw.proc_ywgl_cdkfx_n_dkyeb_xzbldk");
            // count_ywgl_cdkfx_n_dkyeb_xzbldk
            flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
            if (!flag) {
                form.setMissionSuccess(0);
                form.setFailedEtlName("cdkyw_common_init");
                form.setReasonForFailure("ETL脚本[ cdkyw_common_init ]，调度执行失败！");
                System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
            }
        }
        //存储调度-6：P_N_DKYEB - proc_cdkfx_p_n_dkyeb
        if (flag) {
            System.out.println("存储调度-5 ==== 结束调度 ==== 存储调度-6 ==== 开始调度 ====");
            param = new HashMap<>();
            param.put("i_tjyf", tjyf);
            param.put("i_jgdm", jgdm);
            param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_n_dkyeb");
            // count_cdkfx_p_n_dkyeb
            flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
            if (!flag) {
                form.setMissionSuccess(0);
                form.setFailedEtlName("cdkyw_common_init");
                form.setReasonForFailure("ETL脚本[ cdkyw_common_init ]，调度执行失败！");
                System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
            }
        }
        //存储调度-7：P_N_DKFFMX - proc_cdkfx_p_n_dkffmx
        if (flag) {
            System.out.println("存储调度-6 ==== 结束调度 ==== 存储调度-7 ==== 开始调度 ====");
            param = new HashMap<>();
            param.put("i_tjyf", tjyf);
            param.put("i_jgdm", jgdm);
            param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_n_dkffmx");
            // count_cdkfx_p_n_dkffmx
            flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
            if (!flag) {
                form.setMissionSuccess(0);
                form.setFailedEtlName("cdkyw_common_init");
                form.setReasonForFailure("ETL脚本[ cdkyw_common_init ]，调度执行失败！");
                System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
            }
        }
        //存储调度-8：P_N_DKSHMX - proc_cdkfx_p_n_dkshmx
        if (flag) {
            System.out.println("存储调度-7 ==== 结束调度 ==== 存储调度-8 ==== 开始调度 ====");
            param = new HashMap<>();
            param.put("i_tjyf", tjyf);
            param.put("i_jgdm", jgdm);
            param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_n_dkshmx");
            // count_cdkfx_p_n_dkshmx
            flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
            if (!flag) {
                form.setMissionSuccess(0);
                form.setFailedEtlName("cdkyw_common_init");
                form.setReasonForFailure("ETL脚本[ cdkyw_common_init ]，调度执行失败！");
                System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
            }
        }
        //存储调度-9：P_N_DKYEB_DQSHL - proc_cdkfx_p_n_dkyeb_dqshl
        if (flag) {
            System.out.println("存储调度-8 ==== 结束调度 ==== 存储调度-9 ==== 开始调度 ====");
            String startday = iBigdataAutoexecLogService.getSystemConfigParamValue("200000002");
            param = new HashMap<>();
            param.put("i_tjyf", tjyf);
            param.put("i_jgdm", jgdm);
            param.put("i_startday", startday == null ? "20100701" : startday);
            param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_n_dkyeb_dqshl");
            // count_cdkfx_p_n_dkyeb_dqshl
            flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
            if (!flag) {
                form.setMissionSuccess(0);
                form.setFailedEtlName("cdkyw_common_init");
                form.setReasonForFailure("ETL脚本[ cdkyw_common_init ]，调度执行失败！");
                System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
            }
        }
        //存储调度-10：P_DKFX_KHJLKHSJTJ - proc_cdkfx_p_dkfx_khjlkhsjtj
        if (flag) {
            System.out.println("存储调度-9 ==== 结束调度 ==== 存储调度-10 ==== 开始调度 ====");
            String dkqxdb = iBigdataAutoexecLogService.getSystemConfigParamValueNumber("200000003");
            String dkqxdh = iBigdataAutoexecLogService.getSystemConfigParamValueNumber("200000004");
            param = new HashMap<>();
            param.put("i_tjyf", tjyf);
            param.put("i_jgdm", jgdm);
            param.put("ln_default_dkqx_db", dkqxdb == null ? "300000" : dkqxdb);
            param.put("ln_default_dkqx_dh", dkqxdh == null ? "300000" : dkqxdh);
            param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_dkfx_khjlkhsjtj");
            // count_cdkfx_p_dkfx_khjlkhsjtj
            flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
            if (!flag) {
                form.setMissionSuccess(0);
                form.setFailedEtlName("cdkyw_common_init");
                form.setReasonForFailure("ETL脚本[ cdkyw_common_init ]，调度执行失败！");
                System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
            }
        }
        //存储调度-11：P_DKDYRPJS - proc_cdkfx_p_dkdyrpjs
        if (flag) {
            System.out.println("存储调度-10 ==== 结束调度 ==== 存储调度-11 ==== 开始调度 ====");
            //每月开始日期，例如：1
            String kssjd = "";
            //每月结束日期，例如：31
            String jssjd = "";
            //是否跨月，例如：1 否 0 是
            String sfky = "";
            ErpBasWyxcsszVO record = iBigdataAutoexecLogService.getOneErpBasWyxcssz("1");
            if (record != null) {
                kssjd = record.getKsrq();
                jssjd = record.getJsrq();
                sfky  = record.getSfky().toString();
            }
            param = new HashMap<>();
            param.put("i_tjyf", tjyf);
            param.put("i_jgdm", jgdm);
            param.put("lv_kssjd", kssjd);
            param.put("lv_jssjd", jssjd);
            param.put("lv_sfky", sfky);
            param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_dkdyrpjs");
            // count_cdkfx_p_dkdyrpjs
            flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
            if (!flag) {
                form.setMissionSuccess(0);
                form.setFailedEtlName("cdkyw_common_init");
                form.setReasonForFailure("ETL脚本[ cdkyw_common_init ]，调度执行失败！");
                System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
            }
        }
        //存储调度-12：P_DKFX_KHJLDKTJ - proc_cdkfx_p_dkfx_khjldktj
        if (flag) {
            System.out.println("存储调度-11 ==== 结束调度 ==== 存储调度-12 ==== 开始调度 ====");
            param = new HashMap<>();
            param.put("i_tjyf", tjyf);
            param.put("i_jgdm", jgdm);
            param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_dkfx_khjldktj");
            // count_cdkfx_p_dkfx_khjldktj
            flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
            if (!flag) {
                form.setMissionSuccess(0);
                form.setFailedEtlName("cdkyw_common_init");
                form.setReasonForFailure("ETL脚本[ cdkyw_common_init ]，调度执行失败！");
                System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
            }
        }
        //存储调度-13：P_YLJC_KHJLDKKHZBTJ - proc_ywgl_cdkfx_yljc_khjldkkhzbtj
        if (flag) {
            System.out.println("存储调度-12 ==== 结束调度 ==== 存储调度-13 ==== 开始调度 ====");
            param = new HashMap<>();
            param.put("i_tjyf", tjyf);
            param.put("ls_jgdm", jgdm);
            param.put("ls_lrczy", lrczy);
            param.put("etl_task","kiss.domain.application.cdkyw.proc_ywgl_cdkfx_yljc_khjldkkhzbtj");
            // count_ywgl_cdkfx_yljc_khjldkkhzbtj
            flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
            if (!flag) {
                form.setMissionSuccess(0);
                form.setFailedEtlName("cdkyw_common_init");
                form.setReasonForFailure("ETL脚本[ cdkyw_common_init ]，调度执行失败！");
                System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
            }
        }
        //存储调度-14：P_CUSTSTAR_DKTJ - proc_ywgl_cdkfx_custstar_dktj
        if (flag) {
            System.out.println("存储调度-13 ==== 结束调度 ==== 存储调度-14 ==== 开始调度 ====");
            String csz = iBigdataAutoexecLogService.getDailyLoanLimit("CS0001");
            param = new HashMap<>();
            param.put("i_tjyf", tjyf);
            param.put("csz", csz);
            param.put("etl_task","kiss.domain.application.cdkyw.proc_ywgl_cdkfx_custstar_dktj");
            // count_ywgl_cdkfx_custstar_dktj
            flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
            if (!flag) {
                form.setMissionSuccess(0);
                form.setFailedEtlName("cdkyw_common_init");
                form.setReasonForFailure("ETL脚本[ cdkyw_common_init ]，调度执行失败！");
                System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
            }
        }
        //存储调度-15：P_WDCDKFX - proc_cdkfx_bankdeposit_loanstat
        if (flag) {
            System.out.println("存储调度-14 ==== 结束调度 ==== 存储调度-15 ==== 开始调度 ====");
            String ckyeze = iBigdataAutoexecLogService.querySubjectNo("100001002");
            String dkyeze = iBigdataAutoexecLogService.querySubjectNo("100001003");
            param = new HashMap<>();
            param.put("i_tjyf", tjyf);
            param.put("i_jgdm", jgdm);
            param.put("i_lrczy", lrczy);
            param.put("i_ckyeze", ckyeze);
            param.put("i_dkyeze", dkyeze);
            param.put("i_qydm", qydm);
            param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_bankdeposit_loanstat");
            // count_cdkfx_bankdeposit_loanstat
            flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
            if (!flag) {
                form.setMissionSuccess(0);
                form.setFailedEtlName("cdkyw_common_init");
                form.setReasonForFailure("ETL脚本[ cdkyw_common_init ]，调度执行失败！");
                System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
            }
        }
        //存储调度-16：P_DKFX_DKKHCKHBL - P_N_TEMP_DKYEB - proc_cdkfx_p_n_temp_dkyeb
        if (flag) {
            System.out.println("存储调度-15 ==== 结束调度 ==== 存储调度-16 ==== 开始调度 ====");
            param = new HashMap<>();
            param.put("i_tjyf", tjyf);
            param.put("i_jgdm", jgdm);
            param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_n_temp_dkyeb");
            // count_cdkfx_p_n_temp_dkyeb
            flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
            if (!flag) {
                form.setMissionSuccess(0);
                form.setFailedEtlName("cdkyw_common_init");
                form.setReasonForFailure("ETL脚本[ cdkyw_common_init ]，调度执行失败！");
                System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
            }
        }
        //存储调度-17：P_DKFX_DKKHCKHBL - proc_cdkfx_p_dkfx_dkkhckhbl
        if (flag) {
            System.out.println("存储调度-16 ==== 结束调度 ==== 存储调度-17 ==== 开始调度 ====");
            param = new HashMap<>();
            param.put("i_tjyf", tjyf);
            param.put("i_jgdm", jgdm);
            param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_dkfx_dkkhckhbl");
            // count_cdkfx_p_dkfx_dkkhckhbl
            flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
            if (!flag) {
                form.setMissionSuccess(0);
                form.setFailedEtlName("cdkyw_common_init");
                form.setReasonForFailure("ETL脚本[ cdkyw_common_init ]，调度执行失败！");
                System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
            }
        }
        if (flag) {
            System.out.println("截至[ " + new Timestamp(System.currentTimeMillis()) + " ]，每日任务调度执行 - Mission_4 - 客户经理贷款统计，调度成功！");
            form.setMissionSuccess(1);
        } else {
            System.out.println("截至[ " + new Timestamp(System.currentTimeMillis()) + " ]，每日任务调度执行 - Mission_4 - 客户经理贷款统计，调度失败！");
        }
        form.setCompleteTime(new Timestamp(System.currentTimeMillis()));
        iBigdataAutoexecLogService.save(form);
    }
}
