package org.cmms.modules.ckjkpt.jcyj.tdryckgl.jkryzhgl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.checkerframework.checker.units.qual.A;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.jkryzhgl.entity.CkjkptJkryzhgl;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.jkryzhgl.service.ICkjkptJkryzhglService;
import org.cmms.modules.sjxf.hxxt.ckzdkb.entity.Ckzdkb;
import org.cmms.modules.sjxf.hxxt.ckzdkb.service.ICkzdkbService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CkjkptJkryzhglVerify implements IExcelVerifyHandler {
    @Autowired
    private ICkjkptJkryzhglService service;
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
        CkjkptJkryzhgl jkryzhgl = (CkjkptJkryzhgl) var1;
        String subAcctNo = (String) var3;
        jkryzhgl.setSubAcctNo(subAcctNo);
        QueryWrapper<Ckzdkb> ckzdkbQueryWrapper = new QueryWrapper<>();
        ckzdkbQueryWrapper.eq("sub_acct_no",jkryzhgl.getSubAcctNo());
        Ckzdkb check = ckzdkbService.getOne(ckzdkbQueryWrapper,false);
        if (check == null) {
            result.setSuccess(false);
            result.setMsg("该存款账号不存在，请核实！");
            return result;
        }
        QueryWrapper<CkjkptJkryzhgl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sub_acct_no",jkryzhgl.getSubAcctNo());
        queryWrapper.eq("ident_no",jkryzhgl.getIdentNo());
        CkjkptJkryzhgl check1 = service.getOne(queryWrapper,false);
        if (check1 != null) {
            result.setSuccess(false);
            result.setMsg("监控人员账户已存在，请勿重复导入！");
            return result;
        }
        return result;
    }
}
