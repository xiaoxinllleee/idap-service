package org.cmms.modules.tjfx.jcsjgl.cssz.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.tjfx.jcsjgl.cssz.entity.TjfxCssz;
import org.cmms.modules.tjfx.jcsjgl.cssz.service.ITjfxCsszService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class TjfxCszzImportVerify implements IExcelVerifyHandler {
    @Autowired
    private ITjfxCsszService tjfxCsszService;

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
        TjfxCssz zzsfpxx = (TjfxCssz) var1;
        String bz = (String)var3;
        zzsfpxx.setBz(bz);

        QueryWrapper<TjfxCssz> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("csbm",zzsfpxx.getCsbm());
        TjfxCssz tjfxCssz = tjfxCsszService.getOne(queryWrapper,false);
        if (tjfxCssz != null){
            result.setSuccess(false);
            result.setMsg("导入失败，该编码信息已存在！");
            return result;
        }

        zzsfpxx.setLrbz(0);
        zzsfpxx.setLrsj(new Date());
        zzsfpxx.setLrr(sysUser.getUsername());
        return result;
    }
}
