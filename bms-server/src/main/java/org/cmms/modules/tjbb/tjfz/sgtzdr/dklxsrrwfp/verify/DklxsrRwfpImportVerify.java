package org.cmms.modules.tjbb.tjfz.sgtzdr.dklxsrrwfp.verify;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.tjbb.tjfz.sgtzdr.dkhxdjb.vo.DkhxdjbImportVO;
import org.cmms.modules.tjbb.tjfz.sgtzdr.dqshl.vo.DqshlImportVO;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.stereotype.Component;

/**
 * @author 龚辉
 * @date 2023/1/10 9:18 周二
 */
@Component
public class DklxsrRwfpImportVerify implements IExcelVerifyHandler {

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
        DkhxdjbImportVO table = (DkhxdjbImportVO) obj;
        return result;
    }
}
