package org.cmms.modules.tjbb.tjfz.sgtzdr.dkyejzrwfp.verify;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.tjbb.tjfz.sgtzdr.dkyejzrwfp.vo.DkyejzrwfpImportVO;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DkyejzrwfpImportVerify implements IExcelVerifyHandler {

    @Override
    public String[] getNeedVerifyFields() {
        return new String[0];
    }

    @Override
    public void setNeedVerifyFields(String[] arr) {

    }

    /**
     *
     * @param obj 当前对象
     * @param name 当前字段名称
     * @param value 当前字段值
     * @return
     */
    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object obj, String name, Object value) {
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        DkyejzrwfpImportVO table = (DkyejzrwfpImportVO) obj;
        table.setLrbz(0);
        table.setLrr(loginUser.getUsername());
        table.setLrsj(new Date());
        return result;
    }
}
