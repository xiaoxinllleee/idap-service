package org.cmms.modules.tjfx.jcsjgl.zfrwsz.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.modules.tjfx.jcsjgl.cssz.entity.TjfxCssz;
import org.cmms.modules.tjfx.jcsjgl.cssz.service.ITjfxCsszService;
import org.cmms.modules.tjfx.jcsjgl.zfrwsz.entity.TjfxZfrwsz;
import org.cmms.modules.tjfx.jcsjgl.zfrwsz.service.ITjfxZfrwszService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class TjfxZfrwszImportVerify implements IExcelVerifyHandler {

    @Autowired
    private ITjfxZfrwszService tjfxZfrwszService;
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
        TjfxZfrwsz tjfxZfrwsz = (TjfxZfrwsz) var1;
        BigDecimal rw = (BigDecimal)var3;
        tjfxZfrwsz.setRw(rw);
        System.out.println(tjfxZfrwsz.getTjwd());
        if ("MM".equals(tjfxZfrwsz.getTjwd())){
            tjfxZfrwsz.setTjrq(DateUtil.getMonthBeginDay(tjfxZfrwsz.getTjrq()));
        }else if ("YYYY".equals(tjfxZfrwsz.getTjwd())){
            tjfxZfrwsz.setTjrq(cn.hutool.core.date.DateUtil.beginOfYear(tjfxZfrwsz.getTjrq()));
        }else {
            tjfxZfrwsz.setTjrq(cn.hutool.core.date.DateUtil.beginOfQuarter(tjfxZfrwsz.getTjrq()));
        }
        QueryWrapper<TjfxZfrwsz> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tjwd",tjfxZfrwsz.getTjwd());
        queryWrapper.eq("tjrq", tjfxZfrwsz.getTjrq());
        queryWrapper.eq("jgdm",tjfxZfrwsz.getJgdm());
        queryWrapper.eq("khlx",tjfxZfrwsz.getKhlx());
        TjfxZfrwsz tjfxZfrwsz1 = tjfxZfrwszService.getOne(queryWrapper,false);
        if (tjfxZfrwsz1 != null){
            result.setSuccess(false);
            result.setMsg("导入失败，该信息已存在！");
            return result;
        }

        tjfxZfrwsz.setLrbz(0);
        tjfxZfrwsz.setLrsj(new Date());
        tjfxZfrwsz.setLrr(sysUser.getUsername());
        return result;
    }
}
