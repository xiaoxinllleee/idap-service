package org.cmms.modules.hr.xsgl.grtjxs.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.hr.xsgl.grtjxs.entity.ErpPersonalTjxs;
import org.cmms.modules.hr.xsgl.grtjxs.service.IErpPersonalTjxsService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ErpPersonalTjxsImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IErpPersonalTjxsService erpPersonalTjxsService;
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
        ErpPersonalTjxs erpPersonalTjxs = (ErpPersonalTjxs)var1;

        QueryWrapper<ErpPersonalTjxs> check = new QueryWrapper<>();
        check.eq("yggh", erpPersonalTjxs.getYggh());
        check.eq("tjlx", erpPersonalTjxs.getTjlx());
        Date sxrqEnd = erpPersonalTjxs.getSxrqEnd();
        if (sxrqEnd == null) {
            sxrqEnd = DateUtil.parseDateFormat("20991231", "YYYYMMDD");
        }
        check.le("sxrq_begin", sxrqEnd);
        check.and((wrapper) -> {
            wrapper.isNull("sxrq_end").or().ge("sxrq_end", erpPersonalTjxs.getSxrqBegin());
        });
        List<ErpPersonalTjxs> list = erpPersonalTjxsService.list(check);
        if (!list.isEmpty()) {
            result.setSuccess(false);
            result.setMsg("此生效日期区间已存在此员工有效的系数调节信息！");
            return result;
        }
        return result;
    }
}
