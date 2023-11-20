package org.cmms.modules.report.tzsjgl.xtdkqmx.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.report.cwbb.ywzkb.entity.Ywzkb;
import org.cmms.modules.report.cwbb.ywzkb.service.impl.YwzkbServiceImpl;
import org.cmms.modules.report.tzsjgl.xtdkqmx.entity.Xtdkqmx;
import org.cmms.modules.report.tzsjgl.xtdkqmx.service.impl.XtdkqmxServiceImpl;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DkqmxImportVerify implements IExcelVerifyHandler {
    @Autowired
    private XtdkqmxServiceImpl xtdkqmxService;



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
        Xtdkqmx form = (Xtdkqmx) var1;
        Date sjrq = (Date) var3;
        QueryWrapper<Xtdkqmx> queryWrapper = new QueryWrapper<>();
        Xtdkqmx qkmb1 = xtdkqmxService.getOne(queryWrapper);
        xtdkqmxService.update(qkmb1, queryWrapper);
            result.setSuccess(false);
            result.setMsg("导入成功！");
            return result;

    }
}
