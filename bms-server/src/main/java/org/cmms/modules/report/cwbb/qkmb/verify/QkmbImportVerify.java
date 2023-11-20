package org.cmms.modules.report.cwbb.qkmb.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.report.cwbb.qkmb.entity.Qkmb;
import org.cmms.modules.report.cwbb.qkmb.service.impl.QkmbServiceImpl;
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.entity.Cldkhtsjgl;
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.service.ICldkhtsjglService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.Date;

@Component
public class QkmbImportVerify implements IExcelVerifyHandler {
    @Autowired
    private QkmbServiceImpl qkmbService;



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
        Qkmb qkmb = (Qkmb) var1;
        Date sjrq = (Date) var3;
        QueryWrapper<Qkmb> queryWrapper = new QueryWrapper<>();
        Qkmb qkmb1 = qkmbService.getOne(queryWrapper);

            qkmbService.update(qkmb1, queryWrapper);
            result.setSuccess(false);
            result.setMsg("导入成功！");
            return result;

    }
}
