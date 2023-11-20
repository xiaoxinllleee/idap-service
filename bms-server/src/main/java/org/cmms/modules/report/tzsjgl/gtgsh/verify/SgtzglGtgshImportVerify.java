package org.cmms.modules.report.tzsjgl.gtgsh.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.report.tzsjgl.gtgsh.entity.SgtzGtgsh;
import org.cmms.modules.report.tzsjgl.gtgsh.service.ISgtzGtgshService;
import org.cmms.modules.report.tzsjgl.xtdkqmx.entity.Xtdkqmx;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author
 * @date 2022/8/26 23:19 周五
 */
@Component
public class SgtzglGtgshImportVerify implements IExcelVerifyHandler {

    @Autowired
    private ISgtzGtgshService sgtzGtgshService;

    @Override
    public String[] getNeedVerifyFields() {
        return null;
    }

    @Override
    public void setNeedVerifyFields(String[] strings) {

    }

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object var1, String var2, Object var3) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        SgtzGtgsh form = (SgtzGtgsh) var1;
        Date sjrq = (Date) var3;
        QueryWrapper<SgtzGtgsh> queryWrapper = new QueryWrapper<>();
        SgtzGtgsh qkmb1 = sgtzGtgshService.getOne(queryWrapper);
        sgtzGtgshService.update(qkmb1, queryWrapper);
        result.setSuccess(false);
        result.setMsg("导入成功！");
        return result;

    }
}
