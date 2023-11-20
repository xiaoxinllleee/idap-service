package org.cmms.modules.tjbb.tjfz.czbxzhgl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.sjxf.hxxt.ckzdkb.entity.Ckzdkb;
import org.cmms.modules.sjxf.hxxt.ckzdkb.service.ICkzdkbService;
import org.cmms.modules.tjbb.tjfz.czbxzhgl.entity.Czbxzhgl;
import org.cmms.modules.tjbb.tjfz.czbxzhgl.service.ICzbxzhglService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class CzbxzhglImportVerify implements IExcelVerifyHandler {
    @Autowired
    private ICzbxzhglService czbxzhglService;
    @Autowired
    private ICkzdkbService ckzdkbService;



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
        Czbxzhgl czbxzhgl = (Czbxzhgl) var1;
        String ckzh = (String)var3;
        czbxzhgl.setCkzh(ckzh);

        QueryWrapper<Czbxzhgl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ckzh",ckzh);
        Czbxzhgl czbxzhgl1 = czbxzhglService.getOne(queryWrapper);
        if (czbxzhgl1 != null){
            result.setSuccess(false);
            result.setMsg("该账号已存在，请勿重复导入！");
            return result;
        }

     /*   QueryWrapper<Czbxzhgl> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("ckzh",ckzh);*/
        //queryWrapper1.eq("zjhm",czbxzhgl.getZjhm());
        Czbxzhgl one = czbxzhglService.getOne(queryWrapper);
        if (one != null){
            czbxzhglService.remove(queryWrapper);
        }

        QueryWrapper<Ckzdkb> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("sub_acct_no",ckzh);
        Ckzdkb ckzdkb = ckzdkbService.getOne(queryWrapper2);
        if (ckzdkb == null){
            result.setSuccess(false);
            result.setMsg("该账号不存在，请核实！");
            return result;
        }

        czbxzhgl.setJgdm(ckzdkb.getBranchNo());
        czbxzhgl.setKhmc(ckzdkb.getCustName());
        czbxzhgl.setZjhm(ckzdkb.getIdentNo());
        czbxzhgl.setLrbz(0);
        czbxzhgl.setLrsj(new Date());
        czbxzhgl.setLrr(sysUser.getUsername());

        return result;
    }
}
