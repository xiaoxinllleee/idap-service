package org.cmms.modules.report.cwbb.zcfzb.verify;

import org.cmms.modules.report.cwbb.zcfzb.entity.CwbbZcfzb;
import org.cmms.modules.report.cwbb.zcfzb.service.ICwbbZcfzbService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ZcfzbImportVerify implements IExcelVerifyHandler {
    @Autowired
    private ICwbbZcfzbService cwbbZcfzbService;



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
        System.out.println("var1" + var1);
        System.out.println("var3" + var3);
        CwbbZcfzb form = (CwbbZcfzb) var1;
        BigDecimal fzncye = (BigDecimal) var3;
        form.setFzncye(fzncye);

            return result;

    }
}
