package org.cmms.modules.report.sgtzgl.dkyeb.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.report.sgtzgl.dkyeb.entity.SgtzglDkyeb;
import org.cmms.modules.report.sgtzgl.dkyeb.service.ISgtzglDkyebService;
import org.cmms.modules.report.sgtzgl.txdjb.entity.SgtzglTxdjb;
import org.cmms.modules.report.sgtzgl.txdjb.service.ISgtzglTxdjbService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author
 * @date 2022/8/26 23:19 周五
 */
@Component
public class DkyebImportVerify implements IExcelVerifyHandler {

    @Autowired
    private ISgtzglDkyebService sgtzglDkyebService;

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
        SgtzglDkyeb form = (SgtzglDkyeb) var1;
        String yqhbfxbs = (String)var3;
        form.setYqhbfxbs(yqhbfxbs);


//        form.setUpdateBy(sysUser.getUsername());
//        form.setUpdateTime(new Date());
        return result;

    }
}
