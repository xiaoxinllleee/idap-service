package org.cmms.modules.report.sgtzgl.ywzkbxjrmb.vo;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.report.sgtzgl.ywzkbxjrmb.entity.SgtzYwzkbXjrmb;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.stereotype.Component;

@Component
public class YwzkbXjrmbImpVerify implements IExcelVerifyHandler {

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
        SgtzYwzkbXjrmb table = (SgtzYwzkbXjrmb) obj;
        return result;
    }
}
