package org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjtz.verify;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.hr.djpd.khjlxjpd.csgl.pdzbk.entity.Pdzbk;
import org.cmms.modules.hr.djpd.khjlxjpd.csgl.pdzbk.service.IPdzbkService;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.khjljcsjsz.entity.Khjljcsjsz;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.khjljcsjsz.service.IKhjljcsjszService;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjtz.entity.Zbsjtz;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjtz.service.IZbsjtzService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class ZbsjtzImportVerify implements IExcelVerifyHandler {

    @Autowired
    private IZbsjtzService zbsjtzService;

    @Autowired
    private IPdzbkService pdzbkService;

    @Autowired
    private IKhjljcsjszService khjljcsjszService;



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
        Zbsjtz table1 = (Zbsjtz) var1;
        BigDecimal tzz = (BigDecimal)var3;
        table1.setTzz(tzz);

        QueryWrapper<Pdzbk> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("zbid",table1.getSjxid());
        Pdzbk zbk = pdzbkService.getOne(queryWrapper);
        if (zbk==null){
            result.setSuccess(false);
            result.setMsg("未查询到对应的指标信息！");
            return result;
        }

        QueryWrapper<Khjljcsjsz> starstaff = new QueryWrapper<>();
        starstaff.eq("pdzq",table1.getPdzq());
        starstaff.eq("pdrq",table1.getPdrq());
        starstaff.eq("zzbz",table1.getZzbz());
        starstaff.eq("ygxm",table1.getYgxm());
        if (StringUtils.isNotBlank(table1.getKhjlbz())){
            starstaff.eq("khjlbz",table1.getKhjlbz());
        }
        Khjljcsjsz starstaff2 = khjljcsjszService.getOne(starstaff);
        if (starstaff2 == null){
            result.setSuccess(false);
            result.setMsg("未找到对应的客户经理基础信息！");
            return result;
        }
        table1.setGwbz(starstaff2.getGwbz());
        table1.setYggh(starstaff2.getYggh());
        table1.setKhjlbz(starstaff2.getKhjlbz());

        QueryWrapper<Zbsjtz> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("pdzq",table1.getPdzq());
        queryWrapper1.eq("pdrq",table1.getPdrq());
        queryWrapper1.eq("zzbz",table1.getZzbz());
        queryWrapper1.eq("gwbz",table1.getGwbz());
        queryWrapper1.eq("yggh",table1.getYggh());
        queryWrapper1.eq("sjxid",table1.getSjxid());
        Zbsjtz check = zbsjtzService.getOne(queryWrapper1);
        if (check != null){
            zbsjtzService.remove(queryWrapper1);
        }

        table1.setSjxmc(zbk.getZbmc());
        table1.setLrbz(0);
        table1.setLrsj(new Date());
        table1.setLrr(sysUser.getUsername());
        return result;

    }
}
