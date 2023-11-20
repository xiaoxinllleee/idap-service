package org.cmms.modules.report.tzsjgl.xtzdkdjb.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.report.tzsjgl.xtxjtzqkb.entity.Xtxjtzqkb;
import org.cmms.modules.report.tzsjgl.xtzdkdjb.entity.XtZdkdjb;
import org.cmms.modules.report.tzsjgl.xtzdkdjb.service.impl.XtZdkdjbServiceImpl;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 龚辉
 * @date 2022/8/23 17:23 周二
 */
@Component
public class XtZdkdjbImportVerify implements IExcelVerifyHandler {

    @Autowired
    private XtZdkdjbServiceImpl xtZdkdjbService;

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
        XtZdkdjb form = (XtZdkdjb) var1;
        Date sjrq = (Date) var3;
        QueryWrapper<XtZdkdjb> queryWrapper = new QueryWrapper<>();
        XtZdkdjb qkmb1 = xtZdkdjbService.getOne(queryWrapper);
        xtZdkdjbService.update(qkmb1, queryWrapper);
        result.setSuccess(false);
        result.setMsg("导入成功！");
        return result;
    }
}
