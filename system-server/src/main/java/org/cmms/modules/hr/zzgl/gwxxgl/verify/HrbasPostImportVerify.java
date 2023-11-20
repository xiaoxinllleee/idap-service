package org.cmms.modules.hr.zzgl.gwxxgl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasPost;
import org.cmms.modules.hr.zzgl.gwxxgl.service.IHrBasPostService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HrbasPostImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IHrBasPostService hrBasPostService;
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
        Integer gwbz = (Integer) var3;
        //
        QueryWrapper<HrBasPost> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("gwbz", gwbz);
        List<HrBasPost> list = hrBasPostService.list(queryWrapper);
        if (!list.isEmpty()) {
            result.setSuccess(false);
            result.setMsg("岗位标识已经存在！");
            return result;
        }
        return result;
    }
}
