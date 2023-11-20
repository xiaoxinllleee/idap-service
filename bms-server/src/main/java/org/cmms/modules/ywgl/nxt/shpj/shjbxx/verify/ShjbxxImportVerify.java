package org.cmms.modules.ywgl.nxt.shpj.shjbxx.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.cmms.modules.ywgl.nxt.shpj.shjbxx.entity.Shjbxx;
import org.cmms.modules.ywgl.nxt.shpj.shjbxx.service.IShjbxxService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ShjbxxImportVerify implements IExcelVerifyHandler {

    @Autowired
    private IHrBasStaffService hrBasStaffService;


    @Autowired
    private IShjbxxService shjbxxService;


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
        Shjbxx table1 =(Shjbxx)var1;
        String yggh = (String) var3;

        QueryWrapper queryWrapper= new QueryWrapper();
        queryWrapper.eq("yggh",yggh);
        HrBasStaff hr_bas_staff = hrBasStaffService.getOne(queryWrapper);
        if(hr_bas_staff==null){
            result.setSuccess(false);
            result.setMsg("该员工信息不存在！");
            return result;
        }
        QueryWrapper<Shjbxx> queryWrapper2 =new QueryWrapper();
        queryWrapper2.eq("shbm",table1.getShbm());
        Shjbxx one = shjbxxService.getOne(queryWrapper2);
        if(one!=null){
            shjbxxService.remove(queryWrapper2);
        }
        table1.setYggh(yggh);
        table1.setLrr(sysUser.getUsername());
        table1.setLrrq(new Timestamp(System.currentTimeMillis()));
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
