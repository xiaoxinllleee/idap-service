package org.cmms.modules.ywgl.djkyw.djkxxgl.verify;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import org.cmms.modules.hr.yggl.ygrggl.service.IHrBasStaffPostService;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.ywgl.djkyw.djkrygl.entity.Djkrygl;
import org.cmms.modules.ywgl.djkyw.djkrygl.service.IDjkryglService;
import org.cmms.modules.ywgl.djkyw.djkwdgl.entity.Djkwdgl;
import org.cmms.modules.ywgl.djkyw.djkwdgl.service.IDjkwdglService;
import org.cmms.modules.ywgl.djkyw.djkxxgl.entity.Djkxxgl;
import org.cmms.modules.ywgl.djkyw.djkxxgl.service.IDjkxxglService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
@Slf4j
@Component
public class DjkxxlImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IDjkxxglService djkxxglService;
    @Autowired
    private IDjkwdglService djkwdglService;
    @Autowired
    private IDjkryglService djkryglService;
    @Autowired
    private ISysDictService iSysDictService;
    @Autowired
    private IHrBasStaffPostService hrBasStaffPostService;
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
        Djkxxgl table1 =(Djkxxgl)var1;
        Long yqqs = (Long) var3;
        table1.setYqqs(yqqs);
        log.info("===============贷记卡信息管理导入tjyf="+DateUtil.getDateString(table1.getTjyf()));
        log.info("===============贷记卡信息管理导入月初="+ DateUtil.getDateString(DateUtil.getMonthBeginDay(table1.getTjyf())));
        table1.setTjyf(DateUtil.getMonthBeginDay(table1.getTjyf()));
        if (table1.getBalance().doubleValue()<= 0) {
            result.setSuccess(false);
            result.setMsg("余额小于0，跳过该条数据的导入！");
            return result;
        }
        table1.setPutoutSum(table1.getBalance());
        if(table1.getYqqs()<=0){
            result.setSuccess(false);
            result.setMsg("贷记卡账号【" + table1.getAcctNo() + "】的逾期期数必须大于0！");
            return result;
        }
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(table1.getTjyf());
        gc.add(2,Double.valueOf(table1.getYqqs() * -1).intValue());
        table1.setMaturity(new Timestamp(gc.getTimeInMillis()));
        table1.setPutOutDate(new Timestamp(gc.getTimeInMillis()));

        if (table1.getMaturity() == null) {
            result.setSuccess(false);
            result.setMsg("贷款到期日期不能为空");
            return result;
        }

        String strTgh = table1.getTgh().replaceAll(" ","");  //根据推广人员编号截取机构代码和员工号
        String strTgh2 = retTgh(strTgh);

        if(strTgh2.length()<13){
            result.setSuccess(false);
            result.setMsg("推广人员编号格式有误");
            return result;
        }

        String strJgdm = strTgh2.substring(0,8);
        String strJob = strTgh2.substring(8,13);
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("tgjgbh",strJgdm);
        Djkwdgl djkwd = djkwdglService.getOne(queryWrapper,false);
        if (djkwd==null) {
            result.setSuccess(false);
            result.setMsg("请先在贷记卡网点关联中维护推广人员编码的机构代码!");
            return result;
        }else{
            table1.setOrg(djkwd.getJgdm());
        }

        QueryWrapper queryWrapper1=new QueryWrapper();
        queryWrapper1.eq("tgrybh",strJob);
        Djkrygl djkyg = djkryglService.getOne(queryWrapper1,false);

        if (djkyg==null) {
            result.setSuccess(false);
            result.setMsg("请先在贷记卡人员关联中维护推广人员编码对应的员工号!");
            return result;
        }else{
            table1.setJobnumber(djkyg.getYggh());
        }
        String jgmc= iSysDictService.queryTableDictTextByKey("HR_BAS_ORGANIZATION","ZZJC","YWJGDM",table1.getOrg());

        if (jgmc==null||jgmc.equals("")) {
            result.setSuccess(false);
            result.setMsg("机构代码【" + table1.getOrg() + "】错误！");
            return result;
        }


        QueryWrapper<HrBasStaff> queryWrapper3 =new QueryWrapper<HrBasStaff>();
        queryWrapper3.eq("yggh",table1.getJobnumber());
        HrBasStaff hr_bas_staff = hrBasStaffService.getOne(queryWrapper3,false);

        if (hr_bas_staff==null) {
            result.setSuccess(false);
            result.setMsg("员工工号【" + table1.getJobnumber() + "】错误，请检查员工信息表中是否存在此员工工号！");
            return result;
        }
//        if (StringUtils.isEmpty(hr_bas_staff.getKhjlbh())) {
//            result.setSuccess(false);
//            result.setMsg("没有为员工【" + hr_bas_staff.getYggh() + "】配置客户经理标识信息！");
//            return result;
//        }
        if (StringUtils.isNotEmpty(hr_bas_staff.getKhjlbh())) {
            table1.setCustManagerId(hr_bas_staff.getKhjlbh());
        }
        table1.setLrsj(new Timestamp(System.currentTimeMillis()));
        table1.setLrczy(sysUser.getUsername());
        table1.setLrbz(0);
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
