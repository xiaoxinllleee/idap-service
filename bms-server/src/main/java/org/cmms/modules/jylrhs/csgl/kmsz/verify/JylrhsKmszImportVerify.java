package org.cmms.modules.jylrhs.csgl.kmsz.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.jylrhs.csgl.kmsz.entity.JylrhsKmsz;
import org.cmms.modules.jylrhs.csgl.kmsz.entity.JylrhsKmszVO;
import org.cmms.modules.jylrhs.csgl.kmsz.service.IJylrhsKmszService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 经营利润核算（科目设置）导入验证类
 *
 * @author penghr
 * @date 2023年6月8日
 */
@Slf4j
@Component
public class JylrhsKmszImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IJylrhsKmszService jylrhsKmszService;

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
        JylrhsKmszVO form = (JylrhsKmszVO) var1;
        if (form != null) {
            /*QueryWrapper<JylrhsKmsz> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("subject_no1",form.getSubjectNo1());
            JylrhsKmsz record = jylrhsKmszService.getOne(queryWrapper,false);
            if (record != null) {
                result.setSuccess(false);
                result.setMsg("该一级科目号已存在，请核实！");
                return result;
            }*/
            QueryWrapper<JylrhsKmsz> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("subject_no2",form.getSubjectNo2());
            JylrhsKmsz record = jylrhsKmszService.getOne(queryWrapper,false);
            if (record != null) {
                result.setSuccess(false);
                result.setMsg("该二级科目号已存在，请核实！");
                return result;
            }
            queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("subject_no1",form.getSubjectNo1());
            queryWrapper.eq("subject_no2",form.getSubjectNo2());
            record = jylrhsKmszService.getOne(queryWrapper,false);
            if (record != null) {
                result.setSuccess(false);
                result.setMsg("该科目设置已存在，请核实！");
                return result;
            }
        }
        return result;
    }
}
