package org.cmms.modules.report.sgtzgl.xdhtlprxxcx.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.report.sgtzgl.khsxcx.entity.SgtzglKhsxcx;
import org.cmms.modules.report.sgtzgl.khsxcx.service.ISgtzglKhsxcxService;
import org.cmms.modules.report.sgtzgl.xdhtlprxxcx.entity.SgtzglXdhtlprxxcx;
import org.cmms.modules.report.sgtzgl.xdhtlprxxcx.service.ISgtzglXdhtlprxxcxService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author
 * @date 2022/8/26 23:19 周五
 */
@Component
public class XdhtlprxxcxImportVerify implements IExcelVerifyHandler {

    @Autowired
    private ISgtzglXdhtlprxxcxService sgtzglXdhtlprxxcxService;

    @Override
    public String[] getNeedVerifyFields() {
        return null;
    }

    @Override
    public void setNeedVerifyFields(String[] strings) {

    }

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object var1, String var2, Object var3) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        SgtzglXdhtlprxxcx form = (SgtzglXdhtlprxxcx) var1;
        String khjl = (String)var3;
        form.setKhjl(khjl);


        return result;

    }
}
