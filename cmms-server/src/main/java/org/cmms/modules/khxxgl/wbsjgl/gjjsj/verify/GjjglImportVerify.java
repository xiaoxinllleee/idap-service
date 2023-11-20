package org.cmms.modules.khxxgl.wbsjgl.gjjsj.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.khxxgl.wbsjgl.gjjsj.entity.KhxxglWbsjglGjjsj;
import org.cmms.modules.sjxf.hxxt.dkzdkb.entity.Dkzdkb;
import org.cmms.modules.sjxf.hxxt.dkzdkb.service.IDkzdkbService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GjjglImportVerify implements IExcelVerifyHandler {
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
        KhxxglWbsjglGjjsj gjjsj = (KhxxglWbsjglGjjsj) var1;
        if (StringUtils.isEmpty(gjjsj.getZjhm())) {
            result.setSuccess(false);
            result.setMsg("证件号码不能为空");
            return result;
        }

        return result;
    }
}
