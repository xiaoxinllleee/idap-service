package org.cmms.modules.report.sgtzgl.ywdjpdcsb.verify;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.report.sgtzgl.ywdjpdcsb.entity.SgtzYwdjpdcsb;
import org.cmms.modules.report.sgtzgl.ywdjpdcsb.service.ISgtzYwdjpdcsbService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author
 * @date 2022/8/26 23:19 周五
 */
@Component
public class SgtzglYwdjpdcsbImportVerify implements IExcelVerifyHandler {

    @Autowired
    private ISgtzYwdjpdcsbService sgtzYwdjpdcsbService;

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
        SgtzYwdjpdcsb form = (SgtzYwdjpdcsb) var1;
        String csz = (String)var3;
        form.setCsz(csz);


        return result;

    }
}
