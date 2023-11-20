package org.cmms.modules.khxxgl.wbsjgl.xyksj.verify;

import org.cmms.common.util.StringUtils;
import org.cmms.modules.khxxgl.wbsjgl.gjjsj.entity.KhxxglWbsjglGjjsj;
import org.cmms.modules.khxxgl.wbsjgl.xyksj.entity.KhxxglWbsjglXyksj;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.stereotype.Component;

@Component
public class XyksjImportVerify implements IExcelVerifyHandler {
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
        KhxxglWbsjglXyksj xyksj = (KhxxglWbsjglXyksj) var1;
        if (StringUtils.isEmpty(xyksj.getZjhm())) {
            result.setSuccess(false);
            result.setMsg("证件号码不能为空");
            return result;
        }

        return result;
    }
}
