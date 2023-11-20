package org.cmms.modules.ywgl.djkyw.djkkhzr.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.hr.yggl.ygrggl.service.IHrBasStaffPostService;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.ywgl.djkyw.djkkhzr.entity.Djkkhzr;
import org.cmms.modules.ywgl.djkyw.djkkhzr.service.IDjkkhzrService;
import org.cmms.modules.ywgl.djkyw.djkrygl.service.IDjkryglService;
import org.cmms.modules.ywgl.djkyw.djkwdgl.service.IDjkwdglService;
import org.cmms.modules.ywgl.djkyw.djkxxgl.entity.Djkxxgl;
import org.cmms.modules.ywgl.djkyw.djkxxgl.service.IDjkxxglService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class DjkhzrImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IDjkxxglService djkxxglService;
    @Autowired
    private IDjkkhzrService djkkhzrService;
    @Autowired
    private IHrBasStaffService hrBasStaffService;

    @Override
    public String[] getNeedVerifyFields() {
        return null;
    }

    @Override
    public void setNeedVerifyFields(String[] var1) {

    }

    /**
     *
     * @param var1 实体对象，包含设置interHandler的字段之前的所有字段值
     * @param var2 设置interHandler的字段名称
     * @param var3 设置interHandler的字段值
     * @return
     */

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object var1, String var2, Object var3) {
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        Djkkhzr table1 =(Djkkhzr)var1;

       String jobnumberzr= (String) var3;

        //查询贷记卡信息
        QueryWrapper<Djkxxgl> queryWrapper2 =new QueryWrapper();
        queryWrapper2.eq("acct_no",table1.getDjkkh());
        queryWrapper2.orderByDesc("tjyf");

        List<Djkxxgl> list = djkxxglService.list(queryWrapper2);
        if(list==null||list.size()==0){
            result.setSuccess(false);
            result.setMsg("贷记卡不存在！");
            return result;
        }
        Djkxxgl  query =list.get(0);
        QueryWrapper<Djkkhzr> queryWrapper3 =new QueryWrapper();
        queryWrapper3.eq("djkkh",table1.getDjkkh());
        Djkkhzr checkForm = djkkhzrService.getOne(queryWrapper3);

        if (checkForm!=null) {//已经存在本记录
            //删除
            djkkhzrService.remove(queryWrapper3);
        }

        table1.setDjkkh(query.getAcctNo());
        table1.setKhmc(query.getCustName());
        table1.setJgdm(query.getOrg());
        table1.setZjhm(query.getCtfcCd());
        table1.setCustid(query.getCustManagerId());
        if (!StringUtils.isEmpty(table1.getCustid())) {
            QueryWrapper queryWrapper= new QueryWrapper();
            queryWrapper.eq("khjlbh",table1.getCustid());
            HrBasStaff hr_bas_staff = hrBasStaffService.getOne(queryWrapper);
            if(hr_bas_staff==null){
                result.setSuccess(false);
                result.setMsg("员工信息表中没有客户经理标识【" + table1.getCustid() + "】的员工信息！");
                return result;
            }else{
                table1.setJobnumber(hr_bas_staff.getYggh());
            }
        } else {
            result.setSuccess(false);
            result.setMsg("贷记卡信息管理中客户经理标识为空！");
            return result;
        }

        if (StringUtils.isEmpty(jobnumberzr)) {
            table1.setCustidzr("");
            table1.setJobnumberzr("");
            table1.setZzczy(sysUser.getUsername());
            table1.setZzrq(null);
        } else {
            QueryWrapper queryWrapper= new QueryWrapper();
            queryWrapper.eq("yggh",jobnumberzr);
            HrBasStaff staff = hrBasStaffService.getOne(queryWrapper);
            if (staff!=null) {
                table1.setCustidzr(staff.getKhjlbh());
                table1.setJobnumberzr(staff.getYggh());
            }else{
                result.setSuccess(false);
                result.setMsg("员工信息表中没有责任人工号【" + jobnumberzr + "】的员工信息！");
                return result;
            }
        }
        table1.setLrczy(sysUser.getUsername());
        table1.setLrsj(new Timestamp(System.currentTimeMillis()));
        table1.setLrbz(0);
        table1.setDflag(0);

        return result;
    }

    /**
     * 推广人员编号格式化
     * @param tgh
     * @return
     */
    public static String retTgh(String tgh){
        if(tgh!=null&&!"".equals(tgh)){
            tgh = tgh.replace(".","") ;
            return !tgh.equals("")&&tgh.length()>1?tgh.substring(0,tgh.length()).trim():tgh.trim();
        }
        return  "" ;
    }
}
