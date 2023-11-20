package org.cmms.modules.report.sgtzgl.dkqmxgtgsh.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.report.sgtzgl.dkqmx.entity.SgtzglDkqmx;
import org.cmms.modules.report.sgtzgl.dkqmx.service.ISgtzglDkqmxService;
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
public class SgtzglDkqmxGtgshImportVerify implements IExcelVerifyHandler {

    @Autowired
    private ISgtzglDkqmxService sgtzglDkqmxService;

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
        SgtzglDkqmx form = (SgtzglDkqmx) var1;
        String htbh = (String)var3;
        form.setHtbh(htbh);

        QueryWrapper<SgtzglDkqmx> queryWrapper = new QueryWrapper<>();
   /*     queryWrapper.eq("jkrzjhm",form.getJkrzjhm());
        SgtzglDkqmx cwbbzcfzb = sgtzglDkqmxService.getOne(queryWrapper, false);
        if (cwbbzcfzb != null){
            result.setSuccess(false);
            result.setMsg("导入失败,请勿导入重复数据！");
            return result;
        }*/
        return result;

    }
}
