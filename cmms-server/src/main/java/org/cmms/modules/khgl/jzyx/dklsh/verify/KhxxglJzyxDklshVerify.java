package org.cmms.modules.khgl.jzyx.dklsh.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.khgl.jzyx.dklsh.entity.KhxxglJzyxDklsh;
import org.cmms.modules.khgl.jzyx.dklsh.service.IKhxxglJzyxDklshService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class KhxxglJzyxDklshVerify implements IExcelVerifyHandler {
    @Autowired
    private IKhxxglJzyxDklshService khxxglJzyxDklshService;


    @Override
    public String[] getNeedVerifyFields() {
        return null;
    }

    @Override
    public void setNeedVerifyFields(String[] var1) {

    }

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object var1, String var2, Object var3) {
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        System.out.println("var1" + var1);
        System.out.println("var3" + var3);
        KhxxglJzyxDklsh form = (KhxxglJzyxDklsh) var1;
        return result;

    }
}
