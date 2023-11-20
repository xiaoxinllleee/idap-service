package org.cmms.modules.report.sgtzgl.dkhxdjb.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.report.sgtzgl.dkhsdjb.entity.SgtzglDkhsdjb;
import org.cmms.modules.report.sgtzgl.dkhsdjb.service.ISgtzglDkhsdjbService;
import org.cmms.modules.report.sgtzgl.dkhxdjb.entity.SgtzglDkhxdjb;
import org.cmms.modules.report.sgtzgl.dkhxdjb.service.ISgtzglDkhxdjbService;
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
public class DkhxdjbImportVerify implements IExcelVerifyHandler {

    @Autowired
    private ISgtzglDkhxdjbService sgtzglDkhxdjbService;

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
        SgtzglDkhxdjb form = (SgtzglDkhxdjb) var1;
        String dyzrr = (String)var3;
        form.setDyzrr(dyzrr);


        return result;

    }
}
