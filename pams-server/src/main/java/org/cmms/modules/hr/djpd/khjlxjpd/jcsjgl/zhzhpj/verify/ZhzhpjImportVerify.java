package org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zhzhpj.verify;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.khjljcsjsz.entity.Khjljcsjsz;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.khjljcsjsz.service.IKhjljcsjszService;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.pxkspf.entity.Pxkspf;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zhzhpj.entity.Zhzhpj;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zhzhpj.service.IZhzhpjService;
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
public class ZhzhpjImportVerify implements IExcelVerifyHandler {

    @Autowired
    private IZhzhpjService zhzhpjService;

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
        Zhzhpj table1 = (Zhzhpj) var1;
        Integer pjjg = (Integer)var3;
        table1.setPjjg(pjjg);

        //当前年初日期
        Date date = DateUtil.beginOfYear(table1.getPjnf());

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
        queryWrapper1.eq("pjnf",table1.getPjnf());
        Zhzhpj check = zhzhpjService.getOne(queryWrapper1, false);
        if (check != null){
            zhzhpjService.remove(queryWrapper1);
        }

        table1.setLrbz(0);
        table1.setLrsj(new Date());
        table1.setLrr(sysUser.getUsername());
        return result;

    }
}
