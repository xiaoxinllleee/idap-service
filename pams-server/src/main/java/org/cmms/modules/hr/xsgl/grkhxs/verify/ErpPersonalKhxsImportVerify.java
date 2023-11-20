package org.cmms.modules.hr.xsgl.grkhxs.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.hr.xsgl.grkhxs.entity.ErpPersonalKhxs;
import org.cmms.modules.hr.xsgl.grkhxs.service.IErpPersonalKhxsService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ErpPersonalKhxsImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IErpPersonalKhxsService erpPersonalKhxsService;
    @Override
    public String[] getNeedVerifyFields() {
        return null;
    }

    @Override
    public void setNeedVerifyFields(String[] var1) {

    }

    /**
     *
     * @param var1 实体对象，包含设置interHandler的字段之前的所有字段值
     * @param var2 设置interHandler的字段名称
     * @param var3 设置interHandler的字段值
     * @return
     */
    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object var1, String var2, Object var3) {
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        ErpPersonalKhxs erpPersonalKhxs = (ErpPersonalKhxs)var1;
        QueryWrapper<ErpPersonalKhxs> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("yggh", erpPersonalKhxs.getYggh());
        erpPersonalKhxsService.remove(queryWrapper);

        return result;
    }
}
