package org.cmms.modules.report.sgtzgl.cwbbywzkbRmb.verify;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.report.sgtzgl.cwbbywzkb.service.ISgtzCwbbywzkbService;
import org.cmms.modules.report.sgtzgl.cwbbywzkbRmb.entity.SgtzCwbbywzkbRmb;
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
public class CwbbywzkbRmbImportVerify implements IExcelVerifyHandler {

    @Autowired
    private ISgtzCwbbywzkbService sgtzCwbbywzkbService;

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
        SgtzCwbbywzkbRmb form = (SgtzCwbbywzkbRmb) var1;
        return result;

    }
}
