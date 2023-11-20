package org.cmms.modules.report.sgtzgl.cwbbbwkmbWzrmb.verify;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.report.sgtzgl.cwbbbwkmb.service.ISgtzCwbbbwkmbService;
import org.cmms.modules.report.sgtzgl.cwbbbwkmbRmb.entity.SgtzcwbwkmbRmb;
import org.cmms.modules.report.sgtzgl.cwbbbwkmbWzrmb.entity.SgtzcwbwkmbWzrmb;
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
public class CwbbwkmbWzrmbImportVerify implements IExcelVerifyHandler {

    @Autowired
    private ISgtzCwbbbwkmbService sgtzCwbbbwkmbService;

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
        SgtzcwbwkmbWzrmb form = (SgtzcwbwkmbWzrmb) var1;
        String xmdh1 = (String)var3;
        form.setXmdh1(xmdh1);

        return result;

    }
}
