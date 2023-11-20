package org.cmms.modules.report.sgtzgl.cwbbqkmb.verify;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.report.sgtzgl.cwbbbwkmb.entity.SgtzCwbbbwkmb;
import org.cmms.modules.report.sgtzgl.cwbbbwkmb.service.ISgtzCwbbbwkmbService;
import org.cmms.modules.report.sgtzgl.cwbbqkmb.entity.SgtzCwbbqkmb;
import org.cmms.modules.report.sgtzgl.cwbbqkmb.service.ISgtzCwbbqkmbService;
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
public class CwbbqkmbImportVerify implements IExcelVerifyHandler {

    @Autowired
    private ISgtzCwbbqkmbService sgtzCwbbqkmbService;

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
        SgtzCwbbqkmb form = (SgtzCwbbqkmb) var1;
        String xmdh1 = (String)var3;
        form.setXmdh1(xmdh1);
      /*  QueryWrapper<SgtzCwbbbwkmb> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("xmdh",form.getXmdh());
        queryWrapper.eq("xmmc",form.getXmmc());
        SgtzCwbbbwkmb cwbbzcfzb = sgtzCwbbqkmbService.getOne(queryWrapper, false);
        if (cwbbzcfzb != null){
            result.setSuccess(false);
            result.setMsg("导入失败,请勿导入重复数据！");
            return result;
        }*/
        return result;

    }
}
