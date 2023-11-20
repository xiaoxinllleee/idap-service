package org.cmms.modules.hr.yggl.ygrggl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.common.util.BeanUtil;
import org.cmms.common.util.DateUtils;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPostVo;
import org.cmms.modules.hr.yggl.ygrggl.service.IHrBasStaffPostService;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;

@Component
public class HrBasStaffPostTransferImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IHrBasStaffPostService hrBasStaffPostService;
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

        HrBasStaffPostVo hrbasStaffPostVo = (HrBasStaffPostVo) var1;
        //如果离岗日期/入岗日期不为空，校验是否存在无离岗日期的数据
        if (hrbasStaffPostVo.getLgrq() != null || hrbasStaffPostVo.getRgrq() != null) {
            QueryWrapper<HrBasStaffPost> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("zzbz", hrbasStaffPostVo.getYsszhbs());
            queryWrapper.eq("yggh", hrbasStaffPostVo.getYggh());
            queryWrapper.eq("gwbz", hrbasStaffPostVo.getYgwbs());
            queryWrapper.isNull("lgrq");
            List<HrBasStaffPost> list = hrBasStaffPostService.list(queryWrapper);
            if (list.isEmpty()) {
                result.setSuccess(false);
                result.setMsg("未找到离岗日期为空的数据！");
                return result;
            }
        }

        return result;
    }
}
