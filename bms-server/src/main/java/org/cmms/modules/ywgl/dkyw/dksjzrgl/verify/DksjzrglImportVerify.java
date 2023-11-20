package org.cmms.modules.ywgl.dkyw.dksjzrgl.verify;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import org.cmms.modules.hr.yggl.ygrggl.service.IHrBasStaffPostService;
import org.cmms.modules.sjxf.hxxt.dkzdkb.entity.Dkzdkb;
import org.cmms.modules.sjxf.hxxt.dkzdkb.service.IDkzdkbService;
import org.cmms.modules.sjxf.xdxt.dkywjjb.entity.Dkywjjb;
import org.cmms.modules.sjxf.xdxt.dkywjjb.service.IDkywjjbService;
import org.cmms.modules.sjxf.xdxt.txywyeb.entity.Txywyeb;
import org.cmms.modules.sjxf.xdxt.txywyeb.service.ITxywyebService;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.entity.Dksjzrgl;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.entity.DksjzrglHistory;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.entity.DksjzrglImport;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.service.IDksjzrglService;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.service.IDksjzrglServiceHistory;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Component
public class DksjzrglImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IDksjzrglService dksjzrglService;
    @Autowired
    private IDksjzrglServiceHistory dksjzrglServiceHistory;
    @Autowired
    private IDkzdkbService dkzdkbService;

    @Autowired
    private IDkywjjbService dkywjjbService;
    @Autowired
    private IHrBasStaffPostService hrBasStaffPostService;
    @Autowired
    private ITxywyebService txywyebService;

    @Override
    public String[] getNeedVerifyFields() {
        return null;
    }

    @Override
    public void setNeedVerifyFields(String[] var1) {

    }


    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object var1, String var2, Object var3) {
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        DksjzrglImport table1 = (DksjzrglImport) var1;
        Date zzrq= (Date) var3;
        table1.setZzrq(zzrq);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        String SysMonths = dateFormat.format(new Date()).concat("01");
        Date tjyf = table1.getTjyf();
        String tjyfStr = DateUtil.format(tjyf, "yyyyMMdd");
        if (StringUtils.isEmpty(tjyfStr) || tjyfStr.equals(SysMonths)) {
            QueryWrapper<Dksjzrgl> wrapperDkzh = new QueryWrapper<>();
            wrapperDkzh.eq("dkzh", table1.getDkzh());
            //wrapperDkzh.eq("data_date",table1.getDataDate());
            Dksjzrgl dkzh = dksjzrglService.getOne(wrapperDkzh);
            if (dkzh != null) {
                if (dkzh.getLrbz() != 0) {
                    result.setSuccess(false);
                    result.setMsg("已存在手工关联的记录!");
                    return result;
                }
                dksjzrglService.remove(wrapperDkzh);
            }
        } else {
            QueryWrapper<DksjzrglHistory> wrapperDkzh = new QueryWrapper<>();
            wrapperDkzh.eq("dkzh", table1.getDkzh());
            wrapperDkzh.eq("fiscal_date",tjyfStr);
            DksjzrglHistory dkzh = dksjzrglServiceHistory.getOne(wrapperDkzh);
            if (dkzh != null) {
                if (dkzh.getLrbz() != 0) {
                    result.setSuccess(false);
                    result.setMsg("已存在手工关联的记录!");
                    return result;
                }
                dksjzrglServiceHistory.remove(wrapperDkzh);
            }
        }

        int flag = 0;
        Txywyeb  lyxd_txxx=new Txywyeb();
        QueryWrapper queryWrapper =new QueryWrapper();
        queryWrapper.eq("acct_no",table1.getDkzh());
        Dkzdkb lyxd_dkyeb= dkzdkbService.getOne(queryWrapper);

        flag = 0;
        if (lyxd_dkyeb!=null) {
            flag = 1;
        } else {
            lyxd_txxx= txywyebService.getOne(queryWrapper);
            if (lyxd_txxx!=null) {
                flag = 2;
            }
        }
        if (flag == 0) {
            result.setSuccess(false);
            result.setMsg("账号不存在!");
            return result;
        }

        table1.setZhlx(0 + flag);
        if (flag == 1) {
            table1.setDkzh(lyxd_dkyeb.getAcctNo());
            table1.setKhmc(lyxd_dkyeb.getCustName());
            table1.setJgdm(lyxd_dkyeb.getBrNo());
            table1.setZjhm(lyxd_dkyeb.getIdentNo());


            QueryWrapper queryWrapper1 =new QueryWrapper();
            queryWrapper1.eq("acct_no",lyxd_dkyeb.getAcctNo());
            Dkywjjb due = dkywjjbService.getOne(queryWrapper1);
            if(due!=null){
                table1.setCustid(due.getUserId());
            }
        } else if (flag == 2) {
            table1.setDkzh(lyxd_txxx.getAcctNo());
            table1.setKhmc(lyxd_txxx.getCustName());
            table1.setJgdm(lyxd_txxx.getOrg());
            table1.setZjhm("");
            table1.setCustid(lyxd_txxx.getCustId() == null ? "" : lyxd_txxx.getCustId() + "");
        }
        if (StringUtils.isNotEmpty(table1.getCustid())) {
            QueryWrapper queryWrapper1 =new QueryWrapper();
            queryWrapper1.eq("khjlbz",table1.getCustid());
            queryWrapper1.orderByDesc("rglx");
            List<HrBasStaffPost> list = hrBasStaffPostService.list(queryWrapper1);
            if(list!=null &&list.size()>=1){
                HrBasStaffPost hr_bas_staff =list.get(0);
                table1.setJobnumber(hr_bas_staff.getYggh());
            }

        }
        if (StringUtils.isEmpty(table1.getJobnumberzr())) {
            //贷款免责
            table1.setCustidzr("");
            table1.setJobnumberzr("");
            table1.setZzczy(sysUser.getUsername());
            table1.setZzrq(null);
        } else {
            QueryWrapper queryWrapper1 =new QueryWrapper();
            queryWrapper1.eq("yggh",table1.getJobnumberzr());
            queryWrapper1.isNotNull("khjlbz");
            List<HrBasStaffPost> list = hrBasStaffPostService.list(queryWrapper1);
            if(list!=null &&list.size()>=1){
                HrBasStaffPost staff =list.get(0);
                table1.setCustidzr(staff.getKhjlbz());
                table1.setJobnumberzr(staff.getYggh());
            }else{
                result.setSuccess(false);
                result.setMsg("员工信息表中没有责任人工号【" + table1.getJobnumberzr() + "】的员工信息！");
                return result;
            }
        }
        table1.setLrczy(sysUser.getUsername());
        table1.setLrsj(new Timestamp(System.currentTimeMillis()));
        table1.setLrbz(0);
        table1.setDflag(0);
        table1.setGlzrbl(new BigDecimal(100));

        return result;
    }
}
