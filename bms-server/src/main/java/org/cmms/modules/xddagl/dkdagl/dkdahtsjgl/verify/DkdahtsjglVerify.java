package org.cmms.modules.xddagl.dkdagl.dkdahtsjgl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.xddagl.dkdagl.dkdahtsjgl.entity.Dkdahtsjgl;
import org.cmms.modules.xddagl.dkdagl.dkdahtsjgl.service.IDkdahtsjglService;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjglbc.entity.Dkhtsjglbc;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjglbc.service.IDkhtsjglbcService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class DkdahtsjglVerify implements IExcelVerifyHandler {
    @Autowired
    private IDkdahtsjglService dkdahtsjglService;


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
        Dkdahtsjgl table1 = (Dkdahtsjgl) var1;
        String dkpzbc = (String) var3;
        table1.setDkpzbc(dkpzbc);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("hth",table1.getHth());
        Dkdahtsjgl check = dkdahtsjglService.getOne(queryWrapper);
        if (check != null) {
            if(!StringUtils.isEmpty(table1.getDkzrr())){
                check.setDkzrr(table1.getDkzrr());
            }
            if(!StringUtils.isEmpty(table1.getDkpz())){
                check.setDkpz(table1.getDkpz());
            }
            if(!StringUtils.isEmpty(table1.getLxdh())){
                check.setLxdh(table1.getLxdh());
            }
            if(!StringUtils.isEmpty(table1.getLxdz())){
                check.setLxdz(table1.getLxdz());
            }
            dkdahtsjglService.update(table1,queryWrapper);
            result.setSuccess(false);
            result.setMsg("导入成功!");
            return result;
        } else {
            result.setSuccess(false);
            result.setMsg("没找到该合同数据，导入失败!");
            return result;
        }
    }

}




