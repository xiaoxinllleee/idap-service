package org.cmms.modules.report.sgtzgl.gmgyqymd.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.checkerframework.checker.units.qual.A;
import org.cmms.modules.report.sgtzgl.dbpjbxxdjb.entity.SgztDbpjbxxdjb;
import org.cmms.modules.report.sgtzgl.gmgyqymd.entity.SgztGmgyqymd;
import org.cmms.modules.report.sgtzgl.gmgyqymd.service.impl.SgztGmgyqymdServiceImpl;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 龚辉
 * @date 2022/8/27 0:16 周六
 */
@Component
public class GmgyqymdImportVerify implements IExcelVerifyHandler {

    @Autowired
    private SgztGmgyqymdServiceImpl sgztGmgyqymdService;

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
        SgztGmgyqymd form = (SgztGmgyqymd) var1;
       /* QueryWrapper<SgztGmgyqymd> queryWrapper = new QueryWrapper<>();
        SgztGmgyqymd qkmb1 = sgztGmgyqymdService.getOne(queryWrapper);
        sgztGmgyqymdService.update(qkmb1, queryWrapper);
        result.setSuccess(false);
        result.setMsg("导入成功！");*/
        return result;
    }
}
