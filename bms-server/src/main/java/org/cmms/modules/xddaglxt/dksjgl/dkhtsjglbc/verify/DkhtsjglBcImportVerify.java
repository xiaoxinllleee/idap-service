package org.cmms.modules.xddaglxt.dksjgl.dkhtsjglbc.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.entity.Dkhtsjgl;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.service.IDkhtsjglService;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjglbc.entity.Dkhtsjglbc;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjglbc.service.IDkhtsjglbcService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class DkhtsjglBcImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IDkhtsjglbcService dkhtsjglbcService;


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
        Dkhtsjglbc table1 = (Dkhtsjglbc) var1;
        String dkzrr = (String) var3;
        table1.setDkzrr(dkzrr);


        if (StringUtils.isEmpty(table1.getHth())) {
            result.setSuccess(false);
            result.setMsg("合同号不能为空，请重新输入！");
            return result;
        }

        QueryWrapper<Dkhtsjglbc> queryWrapper = new QueryWrapper();
        queryWrapper.eq("hth", table1.getHth());
        Dkhtsjglbc check = dkhtsjglbcService.getOne(queryWrapper);
        if (check != null) {
            result.setSuccess(false);
            result.setMsg("此合同数据已存在！");
            return result;
        } else {
            table1.setLrsj(new Timestamp(System.currentTimeMillis()));
            table1.setLrr(sysUser.getUsername());
            table1.setLrbz(0);
            return result;

        }
    }

}




