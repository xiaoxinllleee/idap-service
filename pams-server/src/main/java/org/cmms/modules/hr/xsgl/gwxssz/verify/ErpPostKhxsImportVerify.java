package org.cmms.modules.hr.xsgl.gwxssz.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.hr.xsgl.gwxssz.entity.ErpPostKhxs;
import org.cmms.modules.hr.xsgl.gwxssz.service.IErpPostKhxsService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ErpPostKhxsImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IErpPostKhxsService erpPostKhxsService;
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
        ErpPostKhxs erpPostKhxs = (ErpPostKhxs)var1;
        QueryWrapper<ErpPostKhxs> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("zzbz", erpPostKhxs.getZzbz());
        queryWrapper.eq("gwbz", erpPostKhxs.getGwbz());
        erpPostKhxsService.remove(queryWrapper);

        return result;
    }
}
