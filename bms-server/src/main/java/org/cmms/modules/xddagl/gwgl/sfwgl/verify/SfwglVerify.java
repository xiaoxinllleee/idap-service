package org.cmms.modules.xddagl.gwgl.sfwgl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.xddagl.dkdagl.dkdahtsjglbc.entity.Dkdahtsjglbc;
import org.cmms.modules.xddagl.dkdagl.dkdahtsjglbc.service.IDkdahtsjglbcService;
import org.cmms.modules.xddagl.gwgl.sfwgl.entity.Sfwgl;
import org.cmms.modules.xddagl.gwgl.sfwgl.service.ISfwglService;
import org.cmms.modules.xddagl.gwgl.sfwgl.entity.Sfwgl;
import org.cmms.modules.xddagl.gwgl.sfwgl.service.ISfwglService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Component
public class SfwglVerify implements IExcelVerifyHandler {
    @Autowired
    private ISfwglService sfwglService;

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
        Sfwgl table1 = (Sfwgl) var1;
        String bz = (String) var3;
        table1.setBz(bz);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("fwbh",table1.getFwbh());
        Sfwgl check = sfwglService.getOne(queryWrapper);
        if (check != null) {
            result.setSuccess(false);
            result.setMsg("此条数据已存在！");
            return result;
        } else {
            table1.setLrsj(new Date());
            table1.setLrr(sysUser.getUsername());
            table1.setLrbz(0);
            return result;
        }





        }
    }





