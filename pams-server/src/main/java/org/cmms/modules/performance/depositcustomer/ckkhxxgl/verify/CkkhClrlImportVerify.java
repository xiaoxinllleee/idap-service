package org.cmms.modules.performance.depositcustomer.ckkhxxgl.verify;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.vo.ClckkhxxImp;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.stereotype.Component;

/**
 * @author 彭浩然
 * @date 2023.04.11
 */
@Component
public class CkkhClrlImportVerify implements IExcelVerifyHandler {

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
        ClckkhxxImp table = (ClckkhxxImp) obj;
        return result;
    }
}
