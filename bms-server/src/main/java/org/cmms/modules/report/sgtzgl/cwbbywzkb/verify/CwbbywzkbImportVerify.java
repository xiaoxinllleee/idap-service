package org.cmms.modules.report.sgtzgl.cwbbywzkb.verify;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.report.sgtzgl.cwbbsybb.entity.SgtzCwbbsybb;
import org.cmms.modules.report.sgtzgl.cwbbsybb.service.ISgtzCwbbsybbService;
import org.cmms.modules.report.sgtzgl.cwbbywzkb.entity.SgtzCwbbywzkb;
import org.cmms.modules.report.sgtzgl.cwbbywzkb.service.ISgtzCwbbywzkbService;
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
public class CwbbywzkbImportVerify implements IExcelVerifyHandler {

    @Autowired
    private ISgtzCwbbywzkbService sgtzCwbbywzkbService;

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
        SgtzCwbbywzkb form = (SgtzCwbbywzkb) var1;
        String xmdh1 = (String) var3;
        form.setXmdh1(xmdh1);
      /*  QueryWrapper<SgtzCwbbywzkb> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("xmdh",form.getXmdh());
        queryWrapper.eq("xmmc",form.getXmmc());
        SgtzCwbbywzkb cwbbzcfzb = sgtzCwbbywzkbService.getOne(queryWrapper, false);
        if (cwbbzcfzb != null){
            result.setSuccess(false);
            result.setMsg("导入失败,请勿导入重复数据！");
            return result;
        }*/
        return result;

    }
}
