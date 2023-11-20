package org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.pxkspf.verify;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.khjljcsjsz.entity.Khjljcsjsz;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.khjljcsjsz.service.IKhjljcsjszService;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.pxkspf.entity.Pxkspf;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.pxkspf.service.IPxkspfService;
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
public class PxkspfImportVerify implements IExcelVerifyHandler {

    @Autowired
    private IPxkspfService pxkspfService;
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
        Pxkspf table1 = (Pxkspf) var1;
        Integer kslx = (Integer)var3;
        table1.setKslx(kslx);

        Date date = DateUtil.beginOfYear(table1.getKssj());

        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("zzbz",table1.getZzbz());
        queryWrapper.eq("yggh",table1.getYggh());
        if (StringUtils.isNotBlank(table1.getKhjlbz())){
            queryWrapper.eq("khjlbz",table1.getKhjlbz());
        }
        queryWrapper.eq("pdrq",date);
        Khjljcsjsz starstaff = khjljcsjszService.getOne(queryWrapper, false);
        if (starstaff == null){
            result.setSuccess(false);
            result.setMsg("未找到对应的客户经理基础信息！");
            return result;
        }
        table1.setGwbz(starstaff.getGwbz());
        table1.setYggh(starstaff.getYggh());
        table1.setKhjlbz(starstaff.getKhjlbz());
        QueryWrapper queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("zzbz",table1.getZzbz());
        queryWrapper1.eq("gwbz",table1.getGwbz());
        queryWrapper1.eq("yggh",table1.getYggh());
        queryWrapper1.eq("kssj",table1.getKssj());
        Pxkspf check = pxkspfService.getOne(queryWrapper1, false);
        if (check != null){
            pxkspfService.remove(queryWrapper1);
        }

        table1.setLrbz(0);
        table1.setLrsj(new Date());
        table1.setLrr(sysUser.getUsername());
        return result;

    }
}
