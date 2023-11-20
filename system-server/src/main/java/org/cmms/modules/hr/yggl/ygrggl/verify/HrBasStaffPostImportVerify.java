package org.cmms.modules.hr.yggl.ygrggl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.common.util.BeanUtil;
import org.cmms.common.util.DateUtils;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
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
public class HrBasStaffPostImportVerify implements IExcelVerifyHandler {
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

        HrBasStaffPost hrbasStaffPost = (HrBasStaffPost) var1;
        QueryWrapper<HrBasStaff> staffQueryWrapper = new QueryWrapper<HrBasStaff>();
        staffQueryWrapper.eq("yggh", hrbasStaffPost.getYggh());
        List<HrBasStaff> staffList = hrBasStaffService.list(staffQueryWrapper);
        if (staffList.isEmpty()) {
            result.setSuccess(false);
            result.setMsg("员工工号不存在！");
            return result;
        }

        HrBasStaffPost check = new HrBasStaffPost();
        BeanUtil.copyPropertiesIgnoreNull(hrbasStaffPost, check);
        if (check.getLgrq() == null) {
            try {
                check.setLgrq(DateUtils.parseDate("20991231", "yyyyMMdd"));
            } catch (ParseException ex) {

            }
        }
        if (!hrBasStaffPostService.ifExistByYgghAndRgrqAndLgrq(check) && hrbasStaffPost.getRglx()==1) {
            result.setSuccess(false);
            result.setMsg("已存在有效入岗信息，不能重复入岗！");
            return result;
        }
        hrbasStaffPost.setId(hrBasStaffPostService.getId());
        return result;
    }
}
