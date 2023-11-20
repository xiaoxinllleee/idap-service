package org.cmms.modules.xyjlcx.jcsjgl.wdjxzhgl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.xyjlcx.jcsjgl.wdjxzhgl.entity.Wdjxzhgl;
import org.cmms.modules.xyjlcx.jcsjgl.wdjxzhgl.service.IWdjxzhglService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class WdjxzhglImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IWdjxzhglService wdjxzhglService;



    @Override
    public String[] getNeedVerifyFields() {
        return null;
    }

    @Override
    public void setNeedVerifyFields(String[] var1) {

    }

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object var1, String var2, Object var3) {
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        Wdjxzhgl wdjxzhgl = (Wdjxzhgl) var1;
        if (StringUtils.isEmpty(wdjxzhgl.getJxzh())) {
            result.setSuccess(false);
            result.setMsg("账号不能为空");
            return result;
        }
        QueryWrapper<Wdjxzhgl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("jgdm", wdjxzhgl.getJgdm());
        Wdjxzhgl wdjxzhgl1 = wdjxzhglService.getOne(queryWrapper);
        if (wdjxzhgl1 != null) {
            wdjxzhglService.remove(queryWrapper);
        }

        wdjxzhgl.setLrbz(0);
        wdjxzhgl.setLrsj(new Timestamp(System.currentTimeMillis()));
        wdjxzhgl.setLrr(sysUser.getUsername());


        return result;
    }
}
