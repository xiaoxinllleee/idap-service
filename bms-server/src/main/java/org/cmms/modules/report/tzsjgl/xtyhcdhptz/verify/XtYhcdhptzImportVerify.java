package org.cmms.modules.report.tzsjgl.xtyhcdhptz.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.report.tzsjgl.xtxjtzqkb.entity.Xtxjtzqkb;
import org.cmms.modules.report.tzsjgl.xtyhcdhptz.entity.XtYhcdhptz;
import org.cmms.modules.report.tzsjgl.xtyhcdhptz.service.XtYhcdhptzService;
import org.cmms.modules.report.tzsjgl.xtyhcdhptz.service.impl.XtYhcdhptzServiceImpl;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 龚辉
 * @date 2022/8/23 14:24 周二
 */
@Component
public class XtYhcdhptzImportVerify implements IExcelVerifyHandler {
    @Autowired
    private XtYhcdhptzServiceImpl xtYhcdhptzService;

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
        XtYhcdhptz form = (XtYhcdhptz) var1;
        Date sjrq = (Date) var3;
        QueryWrapper<XtYhcdhptz> queryWrapper = new QueryWrapper<>();
        XtYhcdhptz qkmb1 = xtYhcdhptzService.getOne(queryWrapper);
        xtYhcdhptzService.update(qkmb1, queryWrapper);
        result.setSuccess(false);
        result.setMsg("导入成功！");
        return result;
    }
}
