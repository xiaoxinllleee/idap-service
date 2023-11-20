package org.cmms.modules.report.cwbb.xyssyb.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.report.cwbb.bwkmb.entity.Bwkmb;
import org.cmms.modules.report.cwbb.bwkmb.service.impl.BwkmbServiceImpl;
import org.cmms.modules.report.cwbb.xyssyb.entity.Xyssyb;
import org.cmms.modules.report.cwbb.xyssyb.service.impl.XyssybServiceImpl;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class XyssybImportVerify implements IExcelVerifyHandler {
    @Autowired
    private XyssybServiceImpl xyssybService;



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
        Xyssyb form = (Xyssyb) var1;
        Date sjrq = (Date) var3;
        QueryWrapper<Xyssyb> queryWrapper = new QueryWrapper<>();
        Xyssyb qkmb1 = xyssybService.getOne(queryWrapper);
        xyssybService.update(qkmb1, queryWrapper);
            result.setSuccess(false);
            result.setMsg("导入成功！");
            return result;

    }
}
