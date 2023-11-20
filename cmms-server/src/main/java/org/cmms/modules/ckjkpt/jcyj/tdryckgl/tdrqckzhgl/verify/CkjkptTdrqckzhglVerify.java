package org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqckzhgl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqckzhgl.entity.CkjkptTdrqckzhgl;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqckzhgl.service.ICkjkptTdrqckzhglService;
import org.cmms.modules.sjxf.hxxt.ckzdkb.entity.Ckzdkb;
import org.cmms.modules.sjxf.hxxt.ckzdkb.service.ICkzdkbService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CkjkptTdrqckzhglVerify implements IExcelVerifyHandler {
    @Autowired
    private ICkjkptTdrqckzhglService service;
    @Autowired
    private ICkzdkbService ckzdkbService;

    @Override
    public String[] getNeedVerifyFields() {
        return null;
    }

    @Override
    public void setNeedVerifyFields(String[] arr) {}

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object var1, String var2, Object var3) {
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        CkjkptTdrqckzhgl tdrqckzhgl = (CkjkptTdrqckzhgl) var1;
        String custName = (String) var3;
        tdrqckzhgl.setCustName(custName);
        QueryWrapper<Ckzdkb> ckzdkbQueryWrapper = new QueryWrapper<>();
        ckzdkbQueryWrapper.eq("sub_acct_no",tdrqckzhgl.getSubAcctNo());
        Ckzdkb check = ckzdkbService.getOne(ckzdkbQueryWrapper,false);
        if (check == null) {
            result.setSuccess(false);
            result.setMsg("该存款账号不存在，请核实！");
            return result;
        }
        QueryWrapper<CkjkptTdrqckzhgl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sub_acct_no",tdrqckzhgl.getSubAcctNo());
        queryWrapper.eq("ident_no",tdrqckzhgl.getIdentNo());
        CkjkptTdrqckzhgl check1 = service.getOne(queryWrapper,false);
        if (check1 != null) {
            result.setSuccess(false);
            result.setMsg("特定人群信息已存在，请勿重复导入！");
            return result;
        }
        return result;
    }
}
