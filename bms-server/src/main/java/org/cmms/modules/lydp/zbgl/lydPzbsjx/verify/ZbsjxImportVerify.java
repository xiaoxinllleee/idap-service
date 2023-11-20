package org.cmms.modules.lydp.zbgl.lydPzbsjx.verify;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.lydp.zbgl.lydPzbsjx.entity.LydpZbsjx;
import org.cmms.modules.lydp.zbgl.lydPzbsjx.service.ILydpZbsjxService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author
 * @date 2022/8/26 23:19 周五
 */
@Component
public class ZbsjxImportVerify implements IExcelVerifyHandler {

    @Autowired
    private ILydpZbsjxService lydpZbsjxService;

    @Override
    public String[] getNeedVerifyFields() {
        return null;
    }

    @Override
    public void setNeedVerifyFields(String[] strings) {

    }

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object var1, String var2, Object var3) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        LydpZbsjx form = (LydpZbsjx) var1;
        String glbm = (String)var3;
        form.setGlbm(glbm);


        return result;

    }
}
