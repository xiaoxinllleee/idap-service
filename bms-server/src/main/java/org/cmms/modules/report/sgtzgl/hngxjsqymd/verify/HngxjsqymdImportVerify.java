package org.cmms.modules.report.sgtzgl.hngxjsqymd.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.report.sgtzgl.gmgyqymd.entity.SgztGmgyqymd;
import org.cmms.modules.report.sgtzgl.hngxjsqymd.entity.SgztHngxjsqymd;
import org.cmms.modules.report.sgtzgl.hngxjsqymd.service.impl.SgztHngxjsqymdServiceImpl;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 龚辉
 * @date 2022/8/27 0:21 周六
 */
@Component
public class HngxjsqymdImportVerify implements IExcelVerifyHandler {

    @Autowired
    private SgztHngxjsqymdServiceImpl sgztHngxjsqymdService;

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
        SgztHngxjsqymd form = (SgztHngxjsqymd) var1;
        String ydqk = (String) var3;
        form.setYdqk(ydqk);
      /*  QueryWrapper<SgztHngxjsqymd> queryWrapper = new QueryWrapper<>();
        SgztHngxjsqymd qkmb1 = sgztHngxjsqymdService.getOne(queryWrapper);
        sgztHngxjsqymdService.update(qkmb1, queryWrapper);
        result.setSuccess(false);
        result.setMsg("导入成功！");*/
        return result;
    }
}
