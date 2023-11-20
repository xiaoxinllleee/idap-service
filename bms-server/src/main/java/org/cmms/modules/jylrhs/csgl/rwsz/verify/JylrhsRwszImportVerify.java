package org.cmms.modules.jylrhs.csgl.rwsz.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.jylrhs.csgl.rwsz.entity.JylrhsRwsz;
import org.cmms.modules.jylrhs.csgl.rwsz.entity.JylrhsRwszVO;
import org.cmms.modules.jylrhs.csgl.rwsz.service.IJylrhsRwszService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 经营利润核算（经营利润任务设置）导入验证类
 *
 * @author penghr
 * @date 2023年6月8日
 */
@Slf4j
@Component
public class JylrhsRwszImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IJylrhsRwszService jylrhsRwszService;

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
        JylrhsRwszVO form = (JylrhsRwszVO) var1;
        if (form != null) {
            QueryWrapper<JylrhsRwsz> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("mission_year",form.getMissionYear());
            queryWrapper.eq("jgdm",form.getJgdm());
            JylrhsRwsz record = jylrhsRwszService.getOne(queryWrapper,false);
            if (record != null) {
                result.setSuccess(false);
                result.setMsg("该条记录已存在，请核实！");
                return result;
            }
        }
        return result;
    }
}
