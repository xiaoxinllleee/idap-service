package org.cmms.modules.ywgl.ywl.xjlljymgl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.ywgl.ywl.xjlljymgl.entity.Xjlljymgl;
import org.cmms.modules.ywgl.ywl.xjlljymgl.service.IXjlljymglService;
import org.cmms.modules.ywgl.ywl.ywlbgl.entity.Ywlbgl;
import org.cmms.modules.ywgl.ywl.ywlbgl.service.IYwlbglService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class XjlljymglImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IXjlljymglService xjlljymglService;
    @Autowired
    private IYwlbglService ywlbglService;


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
        Xjlljymgl xjlljymgl = (Xjlljymgl) var1;
        //Integer sfqy = (Integer)var3;

        QueryWrapper<Xjlljymgl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("jym",xjlljymgl.getJym());
        Xjlljymgl xjlljymgl1 = xjlljymglService.getOne(queryWrapper);
        if (xjlljymgl1 != null){
            result.setSuccess(false);
            result.setMsg("现金流量交易码已经存在！");
            return result;
        }

        QueryWrapper<Ywlbgl> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("dyjym",xjlljymgl.getJym());
        Ywlbgl ywlbgl = ywlbglService.getOne(queryWrapper1);
        if (ywlbgl == null){
            result.setSuccess(false);
            result.setMsg("对应交易码不存在！");
            return result;
        }

        xjlljymgl.setLrr(sysUser.getUsername());
        xjlljymgl.setLrbz(0);
        xjlljymgl.setLrsj(new Date());

        return result;
    }
}
