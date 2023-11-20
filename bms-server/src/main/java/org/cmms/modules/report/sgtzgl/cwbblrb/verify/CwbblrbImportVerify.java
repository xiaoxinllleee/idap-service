package org.cmms.modules.report.sgtzgl.cwbblrb.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.report.sgtzgl.cwbblrb.entity.SgztCwbblrb;
import org.cmms.modules.report.sgtzgl.cwbblrb.service.ISgztCwbblrbService;
import org.cmms.modules.report.sgtzgl.cwbblrb.service.impl.SgztCwbblrbServiceImpl;
import org.cmms.modules.report.sgtzgl.dbpgldkdjb.entity.SgztDbpgldkdjb;
import org.cmms.modules.report.tzsjgl.xtbhdjb.entity.XtBhdjb;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 龚辉
 * @date 2022/8/26 23:19 周五
 */
@Component
public class CwbblrbImportVerify implements IExcelVerifyHandler {

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
        SgztCwbblrb form = (SgztCwbblrb) var1;
        BigDecimal sntqje = (BigDecimal)var3;
        form.setSntqje(sntqje);
       /* QueryWrapper<SgztDbpgldkdjb> queryWrapper = new QueryWrapper<>();
        result.setSuccess(false);
        result.setMsg("导入成功！");*/
        return result;
    }
}
