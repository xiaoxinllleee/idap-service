package org.cmms.modules.ywgl.dkyw.lsdksjgl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.sjxf.hxxt.dkzdkb.entity.Dkzdkb;
import org.cmms.modules.sjxf.hxxt.dkzdkb.service.IDkzdkbService;
import org.cmms.modules.sjxf.xdxt.txywyeb.service.ITxywyebService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.entity.Dksjzrgl;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.service.IDksjzrglService;
import org.cmms.modules.ywgl.dkyw.lsdksjgl.entity.Lsdksjgl;
import org.cmms.modules.ywgl.dkyw.lsdksjgl.service.ILsdksjglService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Component
public class LsdksjglImportVerify implements IExcelVerifyHandler {
    @Autowired
    private ILsdksjglService lsdksjglService;
    @Autowired
    private IHrBasOrganizationService hrBasOrganizationService;

    @Override
    public String[] getNeedVerifyFields() {
        return null;
    }


    @Autowired
    private IDkzdkbService dkzdkbService;

    @Override
    public void setNeedVerifyFields(String[] var1) {

    }

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object var1, String var2, Object var3) {
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        Lsdksjgl lsdksjgl = (Lsdksjgl) var1;
        BigDecimal putoutSum = (BigDecimal) var3;
        lsdksjgl.setPutoutSum(putoutSum);

        QueryWrapper<Dkzdkb> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("acct_no", lsdksjgl.getAcctNo());
        Dkzdkb one1 = dkzdkbService.getOne(queryWrapper2);
        if (one1 != null) {
            result.setSuccess(false);
            result.setMsg("贷款余额表中存在该笔贷款！");
            return result;
        }

        QueryWrapper<HrBasOrganization> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("ywjgdm", lsdksjgl.getOrg());
        HrBasOrganization hrBasOrganization = hrBasOrganizationService.getOne(queryWrapper1);
        if (hrBasOrganization == null) {
            result.setSuccess(false);
            result.setMsg("未找到机构代码对应的机构信息！");
            return result;
        }

        QueryWrapper<Lsdksjgl> lsdksjglQueryWrapper = new QueryWrapper<>();
        lsdksjglQueryWrapper.eq("acct_no",lsdksjgl.getAcctNo());
        Lsdksjgl lsdksjgl1 = lsdksjglService.getOne(lsdksjglQueryWrapper);
        if (lsdksjgl1 != null){
            lsdksjglService.remove(lsdksjglQueryWrapper);
            //lsdksjglService.update();
        }

        lsdksjgl.setFinInsName(hrBasOrganization.getZzmc());
        lsdksjgl.setBalance(new BigDecimal(0));
        lsdksjgl.setLrbz(0);
        lsdksjgl.setLrczy(sysUser.getRealname());
        lsdksjgl.setLrsj(new Timestamp(System.currentTimeMillis()));

        return result;
    }


}
