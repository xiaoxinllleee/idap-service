package org.cmms.modules.report.tzsjgl.xtpjdjp.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.report.tzsjgl.xtpjdjp.entity.XtPjdjp;
import org.cmms.modules.report.tzsjgl.xtpjdjp.service.IXtPjdjpService;
import org.cmms.modules.report.tzsjgl.xtpjdjp.service.impl.XtPjdjpServiceImpl;
import org.cmms.modules.report.tzsjgl.xtyhcdhptz.entity.XtYhcdhptz;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 龚辉
 * @date 2022/8/23 16:02 周二
 */
@Component
public class XtPjdjpImportVerify implements IExcelVerifyHandler {

    @Autowired
    private XtPjdjpServiceImpl xtPjdjpService;

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
        XtPjdjp form = (XtPjdjp) var1;
        Date sjrq = (Date) var3;
        QueryWrapper<XtPjdjp> queryWrapper = new QueryWrapper<>();
        XtPjdjp qkmb1 = xtPjdjpService.getOne(queryWrapper);
        xtPjdjpService.update(qkmb1, queryWrapper);
        result.setSuccess(false);
        result.setMsg("导入成功！");
        return result;
    }
}
