package org.cmms.modules.hr.yggl.ygxxgl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HrbasStaffImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IHrBasStaffService hrBasStaffService;
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
        if (var3 == null) {
            return result;
        }
        String yggh = (String) var3;
        //
        QueryWrapper<HrBasStaff> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("yggh", yggh);
        List<HrBasStaff> list = hrBasStaffService.list(queryWrapper);
        if (!list.isEmpty()) {
            result.setSuccess(false);
            result.setMsg("员工工号已经存在！");
            return result;
        }
        return result;
    }
}
