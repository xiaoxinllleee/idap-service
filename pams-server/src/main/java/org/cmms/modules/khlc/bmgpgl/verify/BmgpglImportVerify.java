package org.cmms.modules.khlc.bmgpgl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.hr.zzgl.gwxxgl.service.IHrBasPostService;
import org.cmms.modules.khlc.bmgpgl.entity.Bmgpgl;
import org.cmms.modules.khlc.bmgpgl.service.IBmgpglService;
import org.cmms.modules.khlc.jczbgl.service.IErpBasZbkService;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class BmgpglImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IBmgpglService bmgpglService;


    @Override
    public String[] getNeedVerifyFields() {
        return null;
    }

    @Override
    public void setNeedVerifyFields(String[] var1) {

    }

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object var1, String var2, Object var3) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        Bmgpgl table1 = (Bmgpgl) var1;
        String zzbz = (String) var3;
        table1.setZzbz(zzbz);

        QueryWrapper<Bmgpgl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bmbz",table1.getBmbz());
        queryWrapper.eq("zzbz",table1.getZzbz());
        Bmgpgl check = bmgpglService.getOne(queryWrapper);
        if (check != null){
            bmgpglService.remove(queryWrapper);
        }
        table1.setLrbz(0);
        table1.setLrsj(new Timestamp(System.currentTimeMillis()));
        table1.setLrczy(sysUser.getUsername());

        return result;

    }
}
