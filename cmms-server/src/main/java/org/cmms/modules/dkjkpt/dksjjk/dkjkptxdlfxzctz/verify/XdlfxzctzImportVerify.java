package org.cmms.modules.dkjkpt.dksjjk.dkjkptxdlfxzctz.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.dkjkpt.dksjjk.dkjkptxdlfxzctz.entity.DkjkptXdlfxzctz;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.stereotype.Component;

@Component
public class XdlfxzctzImportVerify implements IExcelVerifyHandler {

    @Override
    public String[] getNeedVerifyFields() {
        return new String[0];
    }

    @Override
    public void setNeedVerifyFields(String[] arr) {

    }

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object var1, String var2, Object var3) {
        Object var=var1;
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        DkjkptXdlfxzctz tabel = (DkjkptXdlfxzctz) var1;
        String sjrq = (String)var3;
        tabel.setSjrq(sjrq);

/*
        QueryWrapper<HrBasOrganization> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("ywjgdm",zzsfpxx.getJgdm());
        HrBasOrganization one = hrBasOrganizationService.getOne(queryWrapper1,false);
        if (one == null){
            result.setSuccess(false);
            result.setMsg("导入失败，机构代码在组织机构中不存在，请核实！");
            return result;
        }*/
        return result;
    }
}
