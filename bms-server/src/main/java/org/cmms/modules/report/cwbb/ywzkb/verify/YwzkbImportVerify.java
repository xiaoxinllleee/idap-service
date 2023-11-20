package org.cmms.modules.report.cwbb.ywzkb.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.report.cwbb.qkmb.entity.Qkmb;
import org.cmms.modules.report.cwbb.qkmb.service.impl.QkmbServiceImpl;
import org.cmms.modules.report.cwbb.ywzkb.entity.Ywzkb;
import org.cmms.modules.report.cwbb.ywzkb.service.impl.YwzkbServiceImpl;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class YwzkbImportVerify implements IExcelVerifyHandler {
    @Autowired
    private YwzkbServiceImpl ywzkbService;



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
        Ywzkb form = (Ywzkb) var1;
        Date sjrq = (Date) var3;
        QueryWrapper<Ywzkb> queryWrapper = new QueryWrapper<>();
        Ywzkb qkmb1 = ywzkbService.getOne(queryWrapper);
        ywzkbService.update(qkmb1, queryWrapper);
            result.setSuccess(false);
            result.setMsg("导入成功！");
            return result;

    }
}
