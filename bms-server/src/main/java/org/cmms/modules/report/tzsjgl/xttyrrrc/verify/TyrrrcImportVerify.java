package org.cmms.modules.report.tzsjgl.xttyrrrc.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.report.cwbb.ywzkb.entity.Ywzkb;
import org.cmms.modules.report.cwbb.ywzkb.service.impl.YwzkbServiceImpl;
import org.cmms.modules.report.tzsjgl.xttyrrrc.entity.Xttyrrrc;
import org.cmms.modules.report.tzsjgl.xttyrrrc.service.impl.XttyrrrcServiceImpl;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TyrrrcImportVerify implements IExcelVerifyHandler {
    @Autowired
    private XttyrrrcServiceImpl xttyrrrcService;



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
        Xttyrrrc form = (Xttyrrrc) var1;
        Date sjrq = (Date) var3;
        QueryWrapper<Xttyrrrc> queryWrapper = new QueryWrapper<>();
        Xttyrrrc qkmb1 = xttyrrrcService.getOne(queryWrapper);
        xttyrrrcService.update(qkmb1, queryWrapper);
            result.setSuccess(false);
            result.setMsg("导入成功！");
            return result;

    }
}
