package org.cmms.modules.report.sgtzgl.zlxxxcyqymd.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.report.sgtzgl.gmgyqymd.entity.SgztGmgyqymd;
import org.cmms.modules.report.sgtzgl.zlxxxcyqymd.entity.SgztZlxxxcyqymd;
import org.cmms.modules.report.sgtzgl.zlxxxcyqymd.service.impl.SgztZlxxxcyqymdServiceImpl;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 龚辉
 * @date 2022/8/27 0:38 周六
 */
@Component
public class ZlxxxcyqymdImportVerify implements IExcelVerifyHandler {

    @Autowired
    private SgztZlxxxcyqymdServiceImpl sgztZlxxxcyqymdService;

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
        SgztZlxxxcyqymd form = (SgztZlxxxcyqymd) var1;
     /*   QueryWrapper<SgztZlxxxcyqymd> queryWrapper = new QueryWrapper<>();
        SgztZlxxxcyqymd qkmb1 = sgztZlxxxcyqymdService.getOne(queryWrapper);
        sgztZlxxxcyqymdService.update(qkmb1, queryWrapper);
        result.setSuccess(false);
        result.setMsg("导入成功！");*/
        return result;
    }

}
