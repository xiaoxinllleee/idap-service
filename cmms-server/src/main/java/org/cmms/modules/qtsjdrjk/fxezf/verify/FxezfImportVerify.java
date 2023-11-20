package org.cmms.modules.qtsjdrjk.fxezf.verify;

import org.cmms.common.util.StringUtils;
import org.cmms.modules.qtsjdrjk.fxezf.entity.ShywxxFxezh;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.stereotype.Component;

@Component
public class FxezfImportVerify implements IExcelVerifyHandler {
    @Override
    public String[] getNeedVerifyFields() {
        return null;
    }

    @Override
    public void setNeedVerifyFields(String[] var1) {

    }

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object var1, String var2, Object var3) {
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        ShywxxFxezh fxezh = (ShywxxFxezh) var1;
        //String frzjhm = (String)var3;
        if (StringUtils.isEmpty(fxezh.getShmc())) {
            result.setSuccess(false);
            result.setMsg("商户名称不能为空");
            return result;
        }
        if (StringUtils.isEmpty(fxezh.getFrdbxx())) {
            result.setSuccess(false);
            result.setMsg("法人代表姓名不能为空");
            return result;
        }
        if (StringUtils.isEmpty(fxezh.getDrzjhm())) {
            result.setSuccess(false);
            result.setMsg("法人证件号不能为空");
            return result;
        }

        return result;
    }
}
