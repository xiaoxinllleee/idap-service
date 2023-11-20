package org.cmms.modules.jylrhs.csgl.zbk.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.jylrhs.csgl.zbk.entity.JylrhsZbk;
import org.cmms.modules.jylrhs.csgl.zbk.entity.JylrhsZbkVO;
import org.cmms.modules.jylrhs.csgl.zbk.service.IJylrhsZbkService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 经营利润核算（指标库）导入验证类
 *
 * @author penghr
 * @date 2023年6月8日
 */
@Slf4j
@Component
public class JylrhsZbkImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IJylrhsZbkService jylrhsZbkService;

    @Override
    public String[] getNeedVerifyFields() {
        return null;
    }

    @Override
    public void setNeedVerifyFields(String[] strings) {
    }

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object var1, String var2, Object var3) {
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        JylrhsZbkVO form = (JylrhsZbkVO) var1;
        if (form != null) {
            QueryWrapper<JylrhsZbk> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("zbid",form.getZbid());
            JylrhsZbk record = jylrhsZbkService.getOne(queryWrapper,false);
            if (record != null) {
                result.setSuccess(false);
                result.setMsg("该指标已存在，请核实！");
                return result;
            }
        }
        return result;
    }
}
