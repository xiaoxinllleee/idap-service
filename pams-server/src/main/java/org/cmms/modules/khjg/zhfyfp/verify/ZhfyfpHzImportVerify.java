package org.cmms.modules.khjg.zhfyfp.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.khjg.zhfyfp.entity.ZhfyfpHz;
import org.cmms.modules.khjg.zhfyfp.service.IZhfyfpHzService;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class ZhfyfpHzImportVerify implements IExcelVerifyHandler {


    @Autowired
    private IZhfyfpHzService zhfyfpHzService;

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
        ZhfyfpHz zhfyfpHz = (ZhfyfpHz) var1;
        BigDecimal zje = (BigDecimal) var3;
        zhfyfpHz.setZje(zje);

        QueryWrapper<ZhfyfpHz> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fpyf",zhfyfpHz.getFpyf());
        queryWrapper.eq("fylx",zhfyfpHz.getFylx());
        queryWrapper.eq("zzbz",zhfyfpHz.getZzbz());
        ZhfyfpHz zhfyfpHz1 = zhfyfpHzService.getOne(queryWrapper,false);
        if (zhfyfpHz1 != null&&zhfyfpHz1.getFpzt()==1){
            result.setSuccess(false);
            result.setMsg("已存在该类型的分配信息，跳过当前该条记录的导入!");
            return result;
        }else {
            zhfyfpHzService.remove(queryWrapper);
        }

        return result;
    }



}
