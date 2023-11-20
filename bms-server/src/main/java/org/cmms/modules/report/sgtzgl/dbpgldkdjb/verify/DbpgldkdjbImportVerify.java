package org.cmms.modules.report.sgtzgl.dbpgldkdjb.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.report.sgtzgl.dbpgldkdjb.entity.SgztDbpgldkdjb;
import org.cmms.modules.report.sgtzgl.dbpgldkdjb.service.impl.SgztDbpgldkdjbServiceImpl;
import org.cmms.modules.report.tzsjgl.xtbhdjb.entity.XtBhdjb;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 龚辉
 * @date 2022/8/26 23:25 周五
 */
@Component
public class DbpgldkdjbImportVerify implements IExcelVerifyHandler {

    @Autowired
    private SgztDbpgldkdjbServiceImpl sgztDbpgldkdjbService;

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
        SgztDbpgldkdjb form = (SgztDbpgldkdjb) var1;
        String dbpzt = (String)var3;
        form.setDbpzt(dbpzt);
       /* QueryWrapper<SgztDbpgldkdjb> queryWrapper = new QueryWrapper<>();
        SgztDbpgldkdjb qkmb1 = sgztDbpgldkdjbService.getOne(queryWrapper);
        sgztDbpgldkdjbService.update(qkmb1, queryWrapper);
        result.setSuccess(false);
        result.setMsg("导入成功！");*/
        return result;
    }
}
