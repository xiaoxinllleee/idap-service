package org.cmms.modules.tjbb.tjfz.sgtzdr.fndk.verify;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.tjbb.tjfz.sgtzdr.fndk.vo.FndkyeImportVO;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.stereotype.Component;

@Component
public class FndkyeImportVerify implements IExcelVerifyHandler {

    @Override
    public String[] getNeedVerifyFields() {
        return new String[0];
    }

    @Override
    public void setNeedVerifyFields(String[] arr) {

    }

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object obj, String name, Object value) {
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        FndkyeImportVO table = (FndkyeImportVO) obj;
        return result;
    }
}
