package org.cmms.modules.ScheduledTask;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.modules.automission.execlog.service.IBigdataAutoexecLogService;
import org.cmms.modules.system.entity.SysDic;
import org.cmms.modules.system.entity.SysExeinfoLog;
import org.cmms.modules.system.service.ISysDicService;
import org.cmms.modules.system.service.ISysExeinfoLogService;
import org.cmms.modules.system.service.ISysLogService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.ywgl.cdkfx.dkdyrp.entity.ErpBasWyxcssz;
import org.cmms.modules.ywgl.cdkfx.dkdyrp.service.IErpBasWyxcsszService;
import org.cmms.modules.ywgl.cdkfx.khjlcdktj.service.IErpYljcKhjldkkhzbtjService;
import org.cmms.modules.ywgl.cdkfx.sysbascfg.service.ISysBasCfgService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @Description Mission_4 : 每日任务调度执行 - （PKG_DKFX_KHJLDKTJ.P_AUTO_EXEC(null, to_char(p_tmpday,'yyyyMMdd'), system)）
 * @Author Penghr
 * @Date 2021年12月31日 09:25:27
 */
@Slf4j
@Api(tags = "客户经理贷款统计")
@RestController
@RequestMapping("/cdkfx/khjldktj")
public class PkgDkfxKhjlCDktjController implements Job, Serializable {
    @Autowired
    private RedisUtil redisUtil;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;
    @Autowired
    private ISysLogService sysLogService;
    @Autowired
    private ISysBasCfgService iSysBasCfgService;
    @Autowired
    private IErpBasWyxcsszService iErpBasWyxcsszService;
    @Autowired
    private IErpYljcKhjldkkhzbtjService erpYljcKhjldkkhzbtjService;
    @Autowired
    private ISysExeinfoLogService iSysExeinfoLogService;
    @Autowired
    private ISysDicService sysDicService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
//        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//        String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
        SysDic sysDic = sysDicService.queryByCode("101001");
        String qydm = sysDic.getValue();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Long startTime = System.currentTimeMillis();
            log.info(String.format("业务管理/存贷款分析/每月自动跑批/定时任务-开始执行 => "+ simpleDateFormat.format(startTime)));

            SysExeinfoLog exeinfoLog = new SysExeinfoLog();
            exeinfoLog.setLogType(2);
            exeinfoLog.setLogContent("业务管理/存贷款分析/每月自动跑批/定时任务");
            exeinfoLog.setMethod("org.cmms.modules.ScheduledTask.PkgDkfxKhjlCDktjController.execute()");
            exeinfoLog.setExeStartTime(simpleDateFormat.format(startTime));

            Result result = new Result<>();
            boolean flag = true;
            if ("true".equalsIgnoreCase(sfdsjpt)) {
                HashMap<String, String> param = new HashMap<>();

                simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                String i_lrczy   = "SYSTEM";
                String tjyf      = simpleDateFormat.format(new Date());
                String i_tjyf    = tjyf.replaceAll("-", "");
                Date dksjrq      = DateUtil.string2Date(sysLogService.dksjrqBig(),"yyyyMMdd");
                log.info(String.format("业务管理/存贷款分析/每月自动跑批/定时任务-贷款入库最大日期 =>" + simpleDateFormat.format(dksjrq)));
                //根据贷款入库最大日期进行定时任务提取
                tjyf = DateUtil.format(dksjrq, "yyyyMMdd");
                i_tjyf = DateUtil.format(DateUtil.getFirstday_Month(dksjrq, 0), "yyyyMMdd");
                String tjyf_zxrq = tjyf;

//                String tjyf_zxrq = org.cmms.modules.util.DateUtil.getSjQmrq(i_tjyf, dksjrq, "yyyyMMdd");

                //存储调度-1：count_cdkfx_p_n_temp_dkyeb
                if (flag) {
                    System.out.println("存储调度-11 ==== 结束调度 ==== 存储调度-12 ==== 开始调度 ====");
                    String lv_khzr_tablename = "";
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    String format = sdf.format(new Date());
                    if (format.substring(0, 6).equals(i_tjyf.substring(0, 6))) {
                        lv_khzr_tablename = "ERP_BAS_DKZHKHZR WHERE 1=1";
                    } else {
                        lv_khzr_tablename = "ERP_BAS_DKZHKHZR_HIS WHERE fiscal_date = '" + i_tjyf + "'";
                    }
                    try {
                        lv_khzr_tablename = new String(lv_khzr_tablename.getBytes(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    System.out.println("存储调度-1 ==== 开始调度 ====");
                    param.put("i_tjyf", tjyf_zxrq);
                    param.put("lv_khzr_tablename", lv_khzr_tablename);
                    param.put("i_ycrq", i_tjyf);
                    param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_n_temp_dkyeb");
                    // count_cdkfx_p_n_temp_dkyeb
                    flag = EtlUtil.callEtl("cdkyw_common_init", param, 30);
                    if (!flag) {
                        System.out.println(("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！"));
                    }
                }
                //存储调度-2：count_cdkfx_p_n_dkyeb
                if (flag) {
                    param = new HashMap<>();
                    param.put("i_tjyf", i_tjyf);
                    param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_n_dkyeb");
                    // count_cdkfx_p_n_dkyeb
                    flag = EtlUtil.callEtl("cdkyw_common_init", param, 30);
                    if (!flag) {
                        System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
                    }
                }
                //存储调度-3：count_cdkfx_p_n_dkffmx
                if (flag) {
                    param = new HashMap<>();
                    param.put("i_tjyf", i_tjyf);
                    param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_n_dkffmx");
                    // count_cdkfx_p_n_dkffmx
                    flag = EtlUtil.callEtl("cdkyw_common_init", param, 30);
                    if (!flag) {
                        System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
                    }
                }
                //存储调度-4：count_cdkfx_p_n_dkshmx
                if (flag) {
                    param = new HashMap<>();
                    param.put("i_tjyf", i_tjyf);
                    param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_n_dkshmx");
                    // count_cdkfx_p_n_dkshmx
                    flag = EtlUtil.callEtl("cdkyw_common_init", param, 30);
                    if (!flag) {
                        System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
                    }
                }
                //存储调度-5：count_cdkfx_p_n_dkyeb_dqshl
                if (flag) {
                    QueryWrapper queryWrapper = new QueryWrapper();
                    queryWrapper.eq("cfgcode", "200000002");
                    String startday = iSysBasCfgService.getOne(queryWrapper).getCfgvalue();
                    param = new HashMap<>();
                    param.put("i_tjyf", i_tjyf);
                    param.put("i_startday", startday == null ? "20100701" : startday);
                    param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_n_dkyeb_dqshl");
                    // count_cdkfx_p_n_dkyeb_dqshl
                    flag = EtlUtil.callEtl("cdkyw_common_init", param, 30);
                    if (!flag) {
                        System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
                    }
                }
                //存储调度-6：count_cdkfx_p_dkfx_khjlkhsjtj
                if (flag) {
                    QueryWrapper queryWrapper = new QueryWrapper();
                    queryWrapper.eq("cfgcode", "200000003");
                    String dkqxdb = iSysBasCfgService.getOne(queryWrapper).getCfgvalue();
                    queryWrapper = new QueryWrapper();
                    queryWrapper.eq("cfgcode", "200000004");
                    String dkqxdh = iSysBasCfgService.getOne(queryWrapper).getCfgvalue();
                    param = new HashMap<>();
                    param.put("i_tjyf", i_tjyf);
                    param.put("ln_default_dkqx_db", dkqxdb == null ? "300000" : dkqxdb);
                    param.put("ln_default_dkqx_dh", dkqxdh == null ? "300000" : dkqxdh);
                    param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_dkfx_khjlkhsjtj");
                    // count_cdkfx_p_dkfx_khjlkhsjtj
                    flag = EtlUtil.callEtl("cdkyw_common_init", param, 30);
                    if (!flag) {
                        System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
                    }
                }
                //存储调度-7：count_cdkfx_p_dkdyrpjs
                if (flag) {
                    String ld_beginDay = "";        //开始日期
                    String ld_endDay = "";        //结束日期
                    //每月开始日期，例如：1
                    String kssjd = "";
                    //每月结束日期，例如：31
                    String jssjd = "";
                    //是否跨月，例如：1 否 0 是
                    String sfky = "";
                    QueryWrapper<ErpBasWyxcssz> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("qybz", 1);
                    ErpBasWyxcssz record = iErpBasWyxcsszService.getOne(queryWrapper);
                    if (record != null) {
                        kssjd = record.getKsrq();
                        jssjd = record.getJsrq();
                        sfky = record.getSfky().toString();
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    try {
                        Date date = sdf.parse(i_tjyf);
                        if ("0".equals(sfky)) {
                            ld_beginDay = DateUtil.addDay(DateUtil.addMonth(date, -1), (Integer.valueOf(kssjd) - 1));
                            ld_endDay = DateUtil.addDay(date, (Integer.valueOf(jssjd) - 1));
                        } else {
                            ld_beginDay = DateUtil.addDay(date, (Integer.valueOf(kssjd) - 1));
                            ld_endDay = DateUtil.addDay(date, (Integer.valueOf(jssjd) - 1));
                        }
                        if (!ld_beginDay.substring(4, 6).equals(ld_endDay.substring(4, 6))) {
                            ld_endDay = DateUtil.getLastDayString(ld_endDay);
                        }
                        System.out.println(ld_endDay);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    param = new HashMap<>();
                    param.put("ld_beginDay", ld_beginDay);
                    param.put("ld_endDay", ld_endDay);
                    param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_dkdyrpjs");
                    // count_cdkfx_p_dkdyrpjs
                    flag = EtlUtil.callEtl("cdkyw_common_init", param, 30);
                    if (!flag) {
                        System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
                    }
                }
                //存储调度-8：count_cdkfx_p_dkfx_khjldktj
                if (flag) {
                    System.out.println("存储调度-11 ==== 结束调度 ==== 存储调度-12 ==== 开始调度 ====");
                    String lv_tablename = "";
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    String format = sdf.format(new Date());
                    if (format.substring(0, 6).equals(i_tjyf.substring(0, 6))) {
                        lv_tablename = "ERP_BAS_CKZHGLXX WHERE 1=1";
                    } else {
                        lv_tablename = "ERP_BAS_CKZHGLXX_HIS WHERE fiscal_date = '" + i_tjyf + "'";
                    }
                    try {
                        lv_tablename = new String(lv_tablename.getBytes(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    param = new HashMap<>();
                    param.put("i_tjyf", i_tjyf);
                    param.put("i_qmrq", tjyf_zxrq);
                    param.put("lv_tablename", lv_tablename);
                    param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_dkfx_khjldktj");
                    // count_cdkfx_p_dkfx_khjldktj
                    flag = EtlUtil.callEtl("cdkyw_common_init", param, 30);
                    if (!flag) {
                        System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
                    }
                }
                //存储调度-9：count_ywgl_cdkfx_yljc_khjldkkhzbtj
                if (flag) {
                    param = new HashMap<>();
                    param.put("i_qmrq", tjyf_zxrq);
                    param.put("i_tjyf", i_tjyf);
                    param.put("ls_lrczy", i_lrczy);
                    param.put("etl_task","kiss.domain.application.cdkyw.proc_ywgl_cdkfx_yljc_khjldkkhzbtj");
                    // count_ywgl_cdkfx_yljc_khjldkkhzbtj
                    flag = EtlUtil.callEtl("cdkyw_common_init", param, 30);
                    if (!flag) {
                        System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
                    }
                }
                //存储调度-10：count_ywgl_cdkfx_custstar_dktj
                if (flag) {
                    String csz = erpYljcKhjldkkhzbtjService.getDailyLoanLimit("CS0001");
                    String csz0002 = erpYljcKhjldkkhzbtjService.getDailyLoanLimit("CS0002");
                    QueryWrapper queryWrapper = new QueryWrapper();
                    queryWrapper.eq("cfgcode", "200000002");
                    String cfgvalue200000002 = iSysBasCfgService.getOne(queryWrapper).getCfgvalue();
                    queryWrapper = new QueryWrapper();
                    queryWrapper.eq("cfgcode", "200000004");
                    String cfgvalue200000004 = iSysBasCfgService.getOne(queryWrapper).getCfgvalue();
                    param = new HashMap<>();
                    param.put("cfgvalue200000002", cfgvalue200000002);
                    param.put("cfgvalue200000004", cfgvalue200000004);
                    param.put("csz",csz);
                    param.put("csz0002",csz0002);
                    param.put("i_tjyf",i_tjyf);
                    param.put("i_qmrq", tjyf_zxrq);
                    param.put("etl_task","kiss.domain.application.cdkyw.proc_ywgl_cdkfx_custstar_dktj");
                    // count_ywgl_cdkfx_custstar_dktj
                    flag = EtlUtil.callEtl("cdkyw_common_init", param, 30);
                    if (!flag) {
                        System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
                    }
                }
                //存储调度-11：count_cdkfx_bankdeposit_loanstat
                if (flag) {
                    QueryWrapper queryWrapper = new QueryWrapper();
                    queryWrapper.eq("cfgcode", "100001002");
                    String ckyeze = ",";
                    ckyeze += iSysBasCfgService.getOne(queryWrapper).getCfgvalue() + ",";
                    queryWrapper = new QueryWrapper();
                    queryWrapper.eq("cfgcode", "100001003");
                    String dkyeze = ",";
                    dkyeze = iSysBasCfgService.getOne(queryWrapper).getCfgvalue() + ",";
                    param = new HashMap<>();
                    param.put("i_qmrq", tjyf_zxrq);
                    param.put("i_lrczy", i_lrczy);
                    param.put("i_ckyeze", ckyeze);
                    param.put("i_dkyeze", dkyeze);
                    param.put("i_qydm", qydm);
                    param.put("i_tjyf", i_tjyf);
                    param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_bankdeposit_loanstat");
                    // count_cdkfx_bankdeposit_loanstat
                    flag = EtlUtil.callEtl("cdkyw_common_init", param, 30);
                    if (!flag) {
                        System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
                    }
                }
                //存储调度-12：count_cdkfx_p_dkfx_dkkhckhbl
                if (flag) {
                    param = new HashMap<>();
                    param.put("i_qmrq", tjyf_zxrq);
                    param.put("i_tjyf", i_tjyf);
                    param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_dkfx_dkkhckhbl");
                    // count_cdkfx_p_dkfx_dkkhckhbl
                    flag = EtlUtil.callEtl("cdkyw_common_init", param, 30);
                    if (!flag) {
                        System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
                    }
                }
                //存储调度-13：count_ywgl_cdkfx_n_dkyeb_xzbldk
                if (flag) {
                    param = new HashMap<>();
                    param.put("i_tjyf", i_tjyf);
                    param.put("etl_task","kiss.domain.application.cdkyw.proc_ywgl_cdkfx_n_dkyeb_xzbldk");
                    // count_ywgl_cdkfx_n_dkyeb_xzbldk
                    flag = EtlUtil.callEtl("cdkyw_common_init", param, 30);
                    if (!flag) {
                        System.out.println("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
                    }
                }
                result.setSuccess(flag);
            }

            Long endTime   = System.currentTimeMillis();
            log.info(String.format("业务管理/存贷款分析/每月自动跑批/定时任务-执行结束 => "+ simpleDateFormat.format(endTime)));
            log.info(String.format("业务管理/存贷款分析/每月自动跑批/定时任务-执行耗时 => "+ (endTime - startTime) / 1000 + " (s)"));

            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            exeinfoLog.setExeEndTime(simpleDateFormat.format(endTime));
            exeinfoLog.setCostTime(String.valueOf((endTime - startTime) / 1000));
            exeinfoLog.setExeStatus(String.valueOf(flag));
            iSysExeinfoLogService.save(exeinfoLog);

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }
}
