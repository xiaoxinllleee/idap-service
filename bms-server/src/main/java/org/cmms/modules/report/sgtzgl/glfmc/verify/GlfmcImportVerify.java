package org.cmms.modules.report.sgtzgl.glfmc.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.report.sgtzgl.bhjymxb.entity.SgtzglBhjymxb;
import org.cmms.modules.report.sgtzgl.bhjymxb.service.ISgtzglBhjymxbService;
import org.cmms.modules.report.sgtzgl.glfmc.entity.SgtzglGlfmc;
import org.cmms.modules.report.sgtzgl.glfmc.service.ISgtzglGlfmcService;
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
public class GlfmcImportVerify implements IExcelVerifyHandler {

    @Autowired
    private ISgtzglGlfmcService sgtzglGlfmcService;

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
        SgtzglGlfmc form = (SgtzglGlfmc) var1;
        String glrq = (String) var3;
        form.setGlrq(glrq);

       /* QueryWrapper<SgtzglGlfmc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("glrq",form.getGlrq());
        SgtzglGlfmc cwbbzcfzb = sgtzglGlfmcService.getOne(queryWrapper, false);
        if (cwbbzcfzb != null){
            result.setSuccess(false);
            result.setMsg("导入失败,请勿导入重复数据！");
            return result;
        }*/
        return result;

    }
}
