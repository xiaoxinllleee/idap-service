package org.cmms.modules.tjbb.tjfz.sdlywzhgl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.sjxf.hxxt.ckzdkb.entity.Ckzdkb;
import org.cmms.modules.sjxf.hxxt.ckzdkb.service.ICkzdkbService;
import org.cmms.modules.sjxf.hxxt.nbzjykb.entity.Nbzjykb;
import org.cmms.modules.sjxf.hxxt.nbzjykb.service.INbzjykbService;
import org.cmms.modules.tjbb.tjfz.czbxzhgl.entity.Czbxzhgl;
import org.cmms.modules.tjbb.tjfz.sdlywzhgl.entity.Sdlywzhgl;
import org.cmms.modules.tjbb.tjfz.sdlywzhgl.service.ISdlywzhglService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class SdlywzhglImportVerify implements IExcelVerifyHandler {
    @Autowired
    private ISdlywzhglService sdlywzhglService;
    @Autowired
    private INbzjykbService nbzjykbService;



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
        Sdlywzhgl sdlywzhgl = (Sdlywzhgl) var1;
        String zh = (String)var3;
        sdlywzhgl.setZh(zh);

        System.out.println(sdlywzhgl);

        QueryWrapper<Nbzjykb> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("acct_no",sdlywzhgl.getZh());
        Nbzjykb nbzjykb = nbzjykbService.getOne(queryWrapper,false);
        System.out.println(nbzjykb);
        if (nbzjykb == null){
            result.setSuccess(false);
            result.setMsg("内部账交易明细宽表[CBS_GECT_BASE]内不存在该账号，请核实！");
            return result;
        }

        QueryWrapper<Sdlywzhgl> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("jgdm",nbzjykb.getBrNo());
        queryWrapper1.eq("zh",zh);
        Sdlywzhgl sdlywzhgl1 = sdlywzhglService.getOne(queryWrapper1);
        if (sdlywzhgl1 != null){
            sdlywzhglService.remove(queryWrapper1);
        }

        sdlywzhgl.setJgdm(nbzjykb.getBrNo());
        sdlywzhgl.setKhmc(nbzjykb.getAcctName());
        sdlywzhgl.setLrbz(0);
        sdlywzhgl.setLrsj(new Date());
        sdlywzhgl.setLrr(sysUser.getUsername());

        return result;
    }
}
