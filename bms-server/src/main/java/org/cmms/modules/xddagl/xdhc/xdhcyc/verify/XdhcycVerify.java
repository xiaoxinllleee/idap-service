package org.cmms.modules.xddagl.xdhc.xdhcyc.verify;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.xddagl.xdhc.xdhc02.entity.Xdhc02;
import org.cmms.modules.xddagl.xdhc.xdhc02.service.IXdhc02Service;
import org.cmms.modules.xddagl.xdhc.xdhcyc.entity.Xdhcyc;
import org.cmms.modules.xddagl.xdhc.xdhcyc.service.IXdhcycService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;


@Component
public class XdhcycVerify implements IExcelVerifyHandler {
    @Autowired
    private IXdhcycService xdhcycService;

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
        Xdhcyc table1 = (Xdhcyc) var1;
        String sfhc= (String) var3;
        table1.setSfhc(sfhc);

        LambdaQueryWrapper<Xdhcyc> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Xdhcyc::getHth,table1.getHth());
        Xdhcyc check = xdhcycService.getOne(lambdaQueryWrapper);
        if (check != null) {
            result.setSuccess(false);
            result.setMsg("此条数据已存在！");
            return result;
        } else {
            table1.setLrsj(new Timestamp(System.currentTimeMillis()));
            table1.setLrr(sysUser.getUsername());
            table1.setLrsj(new  Timestamp(System.currentTimeMillis()));
            return result;
        }
    }
}
