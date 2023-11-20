package org.cmms.modules.dkjkpt.dksjjk.dksjjktz.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.entity.Dkjlptgzlxt;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.service.IDkjlptgzlxtService;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhbnbldkjgqkb1.entity.QhBnbldkJgqkb1;
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
public class GzlImportVerify implements IExcelVerifyHandler {

    @Autowired
    private IDkjlptgzlxtService dkjlptgzlxtService;

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
        Dkjlptgzlxt form = (Dkjlptgzlxt) var1;
        String month12 = (String)var3;
        form.setMonth12(month12);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("dkzh",form.getDkzh());
        Dkjlptgzlxt gzl = dkjlptgzlxtService.getOne(queryWrapper,false);
        if (gzl != null){
            result.setSuccess(false);
            result.setMsg("导入失败,已存在该账号！");
            return result;
        }

        return result;

    }
}
