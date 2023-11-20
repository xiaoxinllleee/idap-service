package org.cmms.modules.report.sgtzgl.xdckmxb.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.report.sgtzgl.khsxcx.entity.SgtzglKhsxcx;
import org.cmms.modules.report.sgtzgl.khsxcx.service.ISgtzglKhsxcxService;
import org.cmms.modules.report.sgtzgl.xdckmxb.entity.SgtzglXdckmxb;
import org.cmms.modules.report.sgtzgl.xdckmxb.service.ISgtzglXdckmxbService;
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
public class XdckmxbImportVerify implements IExcelVerifyHandler {

    @Autowired
    private ISgtzglXdckmxbService sgtzglXdckmxbService;

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
        SgtzglXdckmxb form = (SgtzglXdckmxb) var1;
        String htzt = (String)var3;
        form.setHtzt(htzt);


        form.setUpdateBy(sysUser.getUsername());
        form.setUpdateTime(new Date());
        return result;

    }
}
