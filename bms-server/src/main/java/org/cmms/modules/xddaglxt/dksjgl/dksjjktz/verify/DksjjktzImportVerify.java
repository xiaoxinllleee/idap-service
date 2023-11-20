package org.cmms.modules.xddaglxt.dksjgl.dksjjktz.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.xddaglxt.dksjgl.dksjjktz.entity.Dksjjktz;
import org.cmms.modules.xddaglxt.dksjgl.dksjjktz.service.IDksjjktzService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class DksjjktzImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IDksjjktzService dksjjktzService;



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
        Dksjjktz dksjjktz = (Dksjjktz) var1;
        String dkzh = (String)var3;

        QueryWrapper<Dksjjktz> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dkzh",dkzh);
        Dksjjktz dksjjktz1 = dksjjktzService.getOne(queryWrapper);


        return result;
    }
}
