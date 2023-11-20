package org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjgl.verify;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.hr.djpd.khjlxjpd.csgl.pdzbk.entity.Pdzbk;
import org.cmms.modules.hr.djpd.khjlxjpd.csgl.pdzbk.service.IPdzbkService;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.khjljcsjsz.entity.Khjljcsjsz;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.khjljcsjsz.service.IKhjljcsjszService;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.pdzbsz.entity.Pdzbsz;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.pdzbsz.service.IPdzbszService;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjgl.entity.Zbsjgl;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjgl.service.IZbsjglService;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjtz.entity.Zbsjtz;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjtz.service.IZbsjtzService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class ZbsjgjImportVerify implements IExcelVerifyHandler {

    @Autowired
    private IZbsjglService zbsjglService;

    @Autowired
    private IHrBasOrganizationService hrBasOrganizationService;

    @Autowired
    private IKhjljcsjszService khjljcsjszService;

    @Autowired
    private IPdzbszService pdzbszService;



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
        Zbsjgl table1 = (Zbsjgl) var1;
        BigDecimal zbjg = (BigDecimal)var3;
        table1.setZbjg(zbjg);



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

        QueryWrapper<Pdzbsz> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("pdzq",table1.getPdzq());
        queryWrapper1.eq("pdrq",table1.getPdrq());
        queryWrapper1.eq("zbid",table1.getZbid());
        Pdzbsz zbk = pdzbszService.getOne(queryWrapper1);
        if (zbk == null){
            result.setSuccess(false);
            result.setMsg("评定指标设置中不存在此指标信息！");
            return result;
        }

        QueryWrapper<Zbsjgl> wrapper = new QueryWrapper<>();
        wrapper.eq("pdzq",table1.getPdzq());
        wrapper.eq("pdrq",table1.getPdrq());
        wrapper.eq("zzbz",table1.getZzbz());
        wrapper.eq("gwbz",table1.getGwbz());
        wrapper.eq("yggh",table1.getYggh());
        wrapper.eq("zbid",table1.getZbid());
        Zbsjgl check = zbsjglService.getOne(wrapper);
        if (check == null){
            result.setSuccess(false);
            result.setMsg("未查询到对应的数据，请先提取指标数据或检查导入数据是否正确！");
            return result;
        }
        if (check.getSjly()==0){
            result.setSuccess(false);
            result.setMsg("不能导入数据来源为系统取数的数据项！");
            return result;
        }
        zbsjglService.remove(wrapper);
        QueryWrapper<HrBasOrganization> organization = new QueryWrapper<>();
        organization.eq("zzbz",table1.getZzbz());
        HrBasOrganization hr = hrBasOrganizationService.getOne(organization);
        if(hr != null){
            if (StringUtils.isNotBlank(hr.getYwjgxz()))
            table1.setYwjgxz(Integer.valueOf(hr.getYwjgxz()));
            if (StringUtils.isNotBlank(hr.getSzqy()))
            table1.setSzqy(Integer.valueOf(hr.getSzqy()));
        }

        table1.setZbmc(zbk.getZbmc());
        table1.setSjly(check.getSjly());
        table1.setLrbz(0);
        table1.setLrsj(new Date());
        table1.setLrr(sysUser.getUsername());
        return result;

    }
}
