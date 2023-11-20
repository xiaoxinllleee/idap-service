package org.cmms.modules.report.sgtzgl.cwbbqkmbRmb.verify;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.report.sgtzgl.cwbbqkmb.entity.SgtzCwbbqkmb;
import org.cmms.modules.report.sgtzgl.cwbbqkmb.service.ISgtzCwbbqkmbService;
import org.cmms.modules.report.sgtzgl.cwbbqkmbRmb.entity.SgtzCwqkmbRmb;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author
 * @date 2022/8/26 23:19 周五
 */
@Component
public class CwbbqkmbRmbImportVerify implements IExcelVerifyHandler {

    @Autowired
    private ISgtzCwbbqkmbService sgtzCwbbqkmbService;

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
        SgtzCwqkmbRmb form = (SgtzCwqkmbRmb) var1;
        return result;

    }
}