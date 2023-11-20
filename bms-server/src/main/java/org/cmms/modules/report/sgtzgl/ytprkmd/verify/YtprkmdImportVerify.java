package org.cmms.modules.report.sgtzgl.ytprkmd.verify;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.report.sgtzgl.bhjymxb.service.ISgtzglBhjymxbService;
import org.cmms.modules.report.sgtzgl.byyzprkmd.entity.SgtzglByyzprkmd;
import org.cmms.modules.report.sgtzgl.ytprkmd.entity.SgtzglYtprkmd;
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
public class YtprkmdImportVerify implements IExcelVerifyHandler {

    @Autowired
    private ISgtzglBhjymxbService sgtzglBhjymxbService;

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
        SgtzglYtprkmd form = (SgtzglYtprkmd) var1;
        String fxxcsj = (String)var3;
        form.setFxxcsj(fxxcsj);

       /* QueryWrapper<SgtzglBhjymxb> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cjrq",form.getCjrq());
        queryWrapper.eq("sqr",form.getSqr());
        queryWrapper.eq("bhbh",form.getBhbh());
        SgtzglBhjymxb cwbbzcfzb = sgtzglBhjymxbService.getOne(queryWrapper, false);
        if (cwbbzcfzb != null){
            result.setSuccess(false);
            result.setMsg("导入失败,请勿导入重复数据！");
            return result;
        }*/
        return result;

    }
}
