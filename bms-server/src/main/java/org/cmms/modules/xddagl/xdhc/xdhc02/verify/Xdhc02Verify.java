package org.cmms.modules.xddagl.xdhc.xdhc02.verify;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import org.cmms.modules.hr.yggl.ygrggl.service.IHrBasStaffPostService;
import org.cmms.modules.sjxf.hxxt.dkzdkb.entity.Dkzdkb;
import org.cmms.modules.sjxf.hxxt.dkzdkb.service.IDkzdkbService;
import org.cmms.modules.sjxf.xdxt.dkywjjb.entity.Dkywjjb;
import org.cmms.modules.sjxf.xdxt.dkywjjb.service.IDkywjjbService;
import org.cmms.modules.sjxf.xdxt.txywyeb.entity.Txywyeb;
import org.cmms.modules.sjxf.xdxt.txywyeb.service.ITxywyebService;
import org.cmms.modules.xddagl.xdhc.xdhc02.entity.Xdhc02;
import org.cmms.modules.xddagl.xdhc.xdhc02.service.IXdhc02Service;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.entity.Dksjzrgl;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.service.IDksjzrglService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Component
public class Xdhc02Verify implements IExcelVerifyHandler {
    @Autowired
    private IXdhc02Service xdhc02Service;

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
        Xdhc02 table1 = (Xdhc02) var1;
        String dkzrr= (String) var3;
        table1.setDkzrr(dkzrr);

        LambdaQueryWrapper<Xdhc02> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Xdhc02::getHth,table1.getHth());


        Xdhc02 check = xdhc02Service.getOne(lambdaQueryWrapper);

        if (check != null) {
            if(!StringUtils.isEmpty(table1.getDkzrr())){
                check.setDkzrr(table1.getDkzrr());
            }
            if(!StringUtils.isEmpty(table1.getDkpz())){
                check.setDkpz(table1.getDkpz());
            }
            if(!StringUtils.isEmpty(table1.getLxdh())){
                check.setLxdh(table1.getLxdh());
            }
            if(!StringUtils.isEmpty(table1.getLxdz())){
                check.setLxdz(table1.getLxdz());
            }
            xdhc02Service.update(check,lambdaQueryWrapper);
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
