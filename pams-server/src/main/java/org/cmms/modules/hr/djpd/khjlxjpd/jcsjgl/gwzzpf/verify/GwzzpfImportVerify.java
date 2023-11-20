package org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.gwzzpf.verify;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.gwzzpf.entity.Gwzzpf;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.gwzzpf.service.IGwzzpfService;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.khjljcsjsz.entity.Khjljcsjsz;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.khjljcsjsz.service.IKhjljcsjszService;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import org.cmms.modules.hr.yggl.ygrggl.service.IHrBasStaffPostService;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
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
public class GwzzpfImportVerify implements IExcelVerifyHandler {

    @Autowired
    private IGwzzpfService gwzzpfService;
    @Autowired
    private IKhjljcsjszService khjljcsjszService;
    @Autowired
    private IHrBasStaffService hrBasStaffService;
    @Autowired
    private IHrBasStaffPostService hrBasStaffPostService;

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
        Gwzzpf table1 = (Gwzzpf) var1;
        Integer jsjczz = (Integer)var3;
        table1.setJsjczz(jsjczz);
        QueryWrapper<Khjljcsjsz> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("zzbz",table1.getZzbz());
        queryWrapper.eq("yggh",table1.getYggh());
        if (StringUtils.isNotBlank(table1.getKhjlbz())){
            queryWrapper.eq("khjlbz",table1.getKhjlbz());
        }
        queryWrapper.orderByDesc("pdrq");
        Khjljcsjsz starstaff = khjljcsjszService.getOne(queryWrapper, false);
        if (starstaff == null){
            result.setSuccess(false);
            result.setMsg("未找到对应的客户经理基础信息！");
            return result;
        }

        QueryWrapper queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("zzbz",table1.getZzbz());
        queryWrapper1.eq("gwbz",starstaff.getGwbz());
        queryWrapper1.eq("yggh",starstaff.getYggh());
        Gwzzpf check = gwzzpfService.getOne(queryWrapper1, false);
        if (check != null){
            gwzzpfService.remove(queryWrapper1);
        }

        table1.setGwbz(starstaff.getGwbz());
        table1.setYggh(starstaff.getYggh());
        table1.setKhjlbz(starstaff.getKhjlbz());
        table1.setLrbz(0);
        table1.setLrsj(new Date());
        table1.setLrr(sysUser.getUsername());
        return result;

    }
}
