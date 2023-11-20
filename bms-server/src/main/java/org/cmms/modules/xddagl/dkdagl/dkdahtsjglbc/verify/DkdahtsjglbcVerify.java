package org.cmms.modules.xddagl.dkdagl.dkdahtsjglbc.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.xddagl.dkdagl.dkdahtsjgl.entity.Dkdahtsjgl;
import org.cmms.modules.xddagl.dkdagl.dkdahtsjgl.service.IDkdahtsjglService;
import org.cmms.modules.xddagl.dkdagl.dkdahtsjglbc.entity.Dkdahtsjglbc;
import org.cmms.modules.xddagl.dkdagl.dkdahtsjglbc.service.IDkdahtsjglbcService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class DkdahtsjglbcVerify implements IExcelVerifyHandler {
    @Autowired
    private IDkdahtsjglbcService dkdahtsjglbcService;


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
        Dkdahtsjglbc table1 = (Dkdahtsjglbc) var1;
        String dkzrr = (String) var3;
        table1.setDkzrr(dkzrr);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("hth",table1.getHth());
        Dkdahtsjglbc check = dkdahtsjglbcService.getOne(queryWrapper);
        if (check != null) {
            result.setSuccess(false);
            result.setMsg("此合同数据已存在！");
            return result;
        } else {
            table1.setLrsj(new Date());
            table1.setLrr(sysUser.getUsername());
            table1.setLrbz(0);
            return result;
        }

        }
    }





