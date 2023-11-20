package org.cmms.modules.khxxgl.khflgl.nhxq.verify;

import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.stereotype.Component;

@Component
public class CamsNhpjsxNsbImportVerify implements IExcelVerifyHandler {
    @Override
    public String[] getNeedVerifyFields() {
        return new String[0];
    }

    @Override
    public void setNeedVerifyFields(String[] strings) {

    }

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object o, String s, Object o1) {
        System.out.println("CamsNhpjsxNsbImportVerify==>");
        System.out.println(o);
        System.out.println(s);
        System.out.println(o1);
        return null;
    }
}
