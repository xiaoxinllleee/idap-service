package org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.sjxf.hxxt.dkzdkb.entity.Dkzdkb;
import org.cmms.modules.sjxf.hxxt.dkzdkb.service.IDkzdkbService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.entity.Dkhtsjgl;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.service.IDkhtsjglService;
import org.cmms.modules.ywgl.dkyw.lsdksjgl.entity.Lsdksjgl;
import org.cmms.modules.ywgl.dkyw.lsdksjgl.service.ILsdksjglService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Component
public class DkhtsjglImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IDkhtsjglService dkhtsjglService;


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
        Dkhtsjgl table1 = (Dkhtsjgl) var1;
        String dkzrr = (String) var3;
        table1.setDkzrr(dkzrr);



        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("hth", table1.getHth());
        Dkhtsjgl check = dkhtsjglService.getOne(queryWrapper);
        if (check != null) {
            if (!StringUtils.isEmpty(table1.getDkzrr())) {
                check.setDkzrr(table1.getDkzrr());
            }
            if (!StringUtils.isEmpty(table1.getDkpz())) {
                check.setDkpz(table1.getDkpz());
            }
            if (!StringUtils.isEmpty(table1.getLxdh())) {
                check.setLxdh(table1.getLxdh());
            }
            if (!StringUtils.isEmpty(table1.getLxdz())) {
                check.setLxdz(table1.getLxdz());
            }
            dkhtsjglService.update(table1,queryWrapper);
            result.setSuccess(false);
            result.setMsg("导入成功！");
            return result;
        } else {
            result.setSuccess(false);
            result.setMsg("没找到该合同数据，导入失败！");
            return result;
        }
    }
}



