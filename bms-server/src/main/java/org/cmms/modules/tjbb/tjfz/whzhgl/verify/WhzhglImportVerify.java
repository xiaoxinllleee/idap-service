package org.cmms.modules.tjbb.tjfz.whzhgl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.sjxf.hxxt.ckzdkb.entity.Ckzdkb;
import org.cmms.modules.sjxf.hxxt.ckzdkb.service.ICkzdkbService;
import org.cmms.modules.tjbb.tjfz.whzhgl.entity.Whzhgl;
import org.cmms.modules.tjbb.tjfz.whzhgl.service.IWhzhglService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
public class WhzhglImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IWhzhglService whzhglService;
    @Autowired
    private ICkzdkbService ckzdkbService;



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
        Whzhgl whzhgl = (Whzhgl) var1;
        String zh = (String) var3;
        whzhgl.setZh(zh);
        System.out.println(whzhgl);
        QueryWrapper<Whzhgl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("zh",whzhgl.getZh());
        //queryWrapper.eq("zjhm",whzhgl.getZjhm());
        //queryWrapper.eq("lx",whzhgl.getLx());
        Whzhgl whzhgl1 = whzhglService.getOne(queryWrapper);
        //List<Whzhgl> whzhgl1 = whzhglService.list(queryWrapper);
        if (whzhgl1 != null){
            result.setSuccess(false);
            result.setMsg("该账号信息已存在，请勿重复导入！");
            return result;
        }

       /* QueryWrapper<Whzhgl> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("zh",zh);
        queryWrapper1.eq("zjhm",whzhgl.getZjhm());*/
        //queryWrapper1.eq("lx",whzhgl.getLx());
        Whzhgl one = whzhglService.getOne(queryWrapper);
        if (one != null){
            whzhglService.remove(queryWrapper);
        }

        QueryWrapper<Ckzdkb> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("sub_acct_no",zh);
        Ckzdkb ckzdkb = ckzdkbService.getOne(queryWrapper2);
        if (ckzdkb == null){
            result.setSuccess(false);
            result.setMsg("该账号不存在，请核实！");
            return result;
        }
        whzhgl.setJgdm(ckzdkb.getBranchNo());
        whzhgl.setKhmc(ckzdkb.getCustName());
        whzhgl.setZjhm(ckzdkb.getIdentNo());
        whzhgl.setLrbz(0);
        whzhgl.setLrr(sysUser.getUsername());
        whzhgl.setLrsj(new Date());

        return result;
    }
}
