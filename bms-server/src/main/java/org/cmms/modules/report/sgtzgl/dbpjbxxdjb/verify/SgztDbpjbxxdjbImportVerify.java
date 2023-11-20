package org.cmms.modules.report.sgtzgl.dbpjbxxdjb.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.checkerframework.checker.units.qual.A;
import org.cmms.modules.report.sgtzgl.dbpgldkdjb.entity.SgztDbpgldkdjb;
import org.cmms.modules.report.sgtzgl.dbpjbxxdjb.entity.SgztDbpjbxxdjb;
import org.cmms.modules.report.sgtzgl.dbpjbxxdjb.service.impl.SgztDbpjbxxdjbServiceImpl;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author 龚辉
 * @date 2022/8/27 0:08 周六
 */
@Component
public class SgztDbpjbxxdjbImportVerify implements IExcelVerifyHandler {

    @Autowired
    private SgztDbpjbxxdjbServiceImpl sgztDbpjbxxdjbService;

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
        SgztDbpjbxxdjb form = (SgztDbpjbxxdjb) var1;
        BigDecimal dkye = (BigDecimal)var3;
        form.setDkye(dkye);
   /*     QueryWrapper<SgztDbpjbxxdjb> queryWrapper = new QueryWrapper<>();
        SgztDbpjbxxdjb qkmb1 = sgztDbpjbxxdjbService.getOne(queryWrapper);
        sgztDbpjbxxdjbService.update(qkmb1, queryWrapper);
        result.setSuccess(false);
        result.setMsg("导入成功！");*/
        return result;
    }
}
