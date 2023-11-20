package org.cmms.modules.system.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HrbasOrganizationImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IHrBasOrganizationService hrBasOrganizationService;
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
        System.out.println("HrbasOrganizationImportVerify==>");
        System.out.println(var1);
        System.out.println(var2);
        System.out.println(var3);

        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        if (var3 == null) {
            return result;
        }
        String zzbz = (String) var3;
        //
        QueryWrapper<HrBasOrganization> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("zzbz", zzbz);
        List<HrBasOrganization> list = hrBasOrganizationService.list(queryWrapper);
        if (!list.isEmpty()) {
            result.setSuccess(false);
            result.setMsg("组织标识已经存在！");
            return result;
        }
        return result;
    }
}
