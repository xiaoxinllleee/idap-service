package org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbrwgl.verify;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.hr.djpd.khjlxjpd.csgl.pdzbk.entity.Pdzbk;
import org.cmms.modules.hr.djpd.khjlxjpd.csgl.pdzbk.service.IPdzbkService;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.khjljcsjsz.entity.Khjljcsjsz;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.khjljcsjsz.service.IKhjljcsjszService;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbrwgl.entity.Zbrwgl;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbrwgl.service.IZbrwglService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysDictService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;


@Component
public class ZbrwglImportVerify implements IExcelVerifyHandler {

    @Autowired
    private IZbrwglService zbrwglService;

    @Autowired
    private IPdzbkService pdzbkService;

    @Autowired
    private IKhjljcsjszService khjljcsjszService;

    @Autowired
    ISysDictService sysDictService;
    @Autowired
    private IHrBasOrganizationService hrBasOrganizationService;

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
        Zbrwgl table1 = (Zbrwgl) var1;
        BigDecimal zbrw = (BigDecimal)var3;
        table1.setZbrw(zbrw);

        QueryWrapper<Pdzbk> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("zbid",table1.getZbid());
        Pdzbk zbk = pdzbkService.getOne(queryWrapper);
        if (zbk==null){
            result.setSuccess(false);
            result.setMsg("指标库中不存在此指标信息！");
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

        QueryWrapper<Zbrwgl> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("pdzq",table1.getPdzq());
        queryWrapper1.eq("pdrq",table1.getPdrq());
        queryWrapper1.eq("zzbz",table1.getZzbz());
        queryWrapper1.eq("gwbz",table1.getGwbz());
        queryWrapper1.eq("yggh",table1.getYggh());
        queryWrapper1.eq("zbid",table1.getZbid());
        Zbrwgl check = zbrwglService.getOne(queryWrapper1);
        if (check != null){
            zbrwglService.remove(queryWrapper1);
        }

        QueryWrapper<HrBasOrganization> organizationQueryWrapper = new QueryWrapper<>();
        organizationQueryWrapper.eq("zzbz",table1.getZzbz());
        HrBasOrganization organization = hrBasOrganizationService.getOne(organizationQueryWrapper,false);
        if (organization == null){
            result.setSuccess(false);
            result.setMsg("组织信息中不存在此组织！");
            return result;
        }else {
            if (StringUtils.isNotBlank(organization.getYwjgxz()))
            table1.setYwjgxz(Integer.valueOf(organization.getYwjgxz()));
            if (StringUtils.isNotBlank(organization.getSzqy()))
            table1.setSzqy(Integer.valueOf(organization.getSzqy()));
        }

        table1.setZbmc(zbk.getZbmc());
        table1.setLrbz(0);
        table1.setLrsj(new Date());
        table1.setLrr(sysUser.getUsername());
        return result;

    }
}
