package org.cmms.modules.tjfx.tjfxJkhtdjb.verify;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.tjfx.tjfxHnkd.entity.TjfxHnkd;
import org.cmms.modules.tjfx.tjfxHnkd.service.ITjfxHnkdService;
import org.cmms.modules.tjfx.tjfxJkhtdjb.entity.TjfxJkhtdjb;
import org.cmms.modules.tjfx.tjfxJkhtdjb.service.ITjfxJkhtdjbService;
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
public class TjfxJkhtdjbImportVerify implements IExcelVerifyHandler {

    @Autowired
    private ITjfxJkhtdjbService tjfxJkhtdjbService;

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
        TjfxJkhtdjb form = (TjfxJkhtdjb) var1;
        String khjl = (String)var3;
        form.setKhjl(khjl);
        form.setCreateTime(new Date());
        form.setCreateBy(sysUser.getUsername());

        return result;

    }
}
