package org.cmms.modules.report.sgtzgl.cwbblrbWzrmb.verify;

import org.cmms.modules.report.sgtzgl.cwbblrb.service.impl.SgztCwbblrbServiceImpl;
import org.cmms.modules.report.sgtzgl.cwbblrbRmb.entity.SgtzCwbblrbRmb;
import org.cmms.modules.report.sgtzgl.cwbblrbWzrmb.entity.SgtzCwbblrbWzrmb;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author 龚辉
 * @date 2022/8/26 23:19 周五
 */
@Component
public class CwbblrbWzrmbImportVerify implements IExcelVerifyHandler {

    @Autowired
    private SgztCwbblrbServiceImpl sgztCwbblrbService;

    @Override
    public String[] getNeedVerifyFields() {
        return null;
    }

    @Override
    public void setNeedVerifyFields(String[] strings) {

    }

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object var1, String var2, Object var3) {
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        System.out.println("var1" + var1);
        System.out.println("var3" + var3);
        SgtzCwbblrbWzrmb form = (SgtzCwbblrbWzrmb) var1;
        BigDecimal sntqje = (BigDecimal)var3;
        form.setSntqje(sntqje);
       /* QueryWrapper<SgztDbpgldkdjb> queryWrapper = new QueryWrapper<>();
        result.setSuccess(false);
        result.setMsg("导入成功！");*/
        return result;
    }
}
