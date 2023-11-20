package org.cmms.modules.ywgl.zzsfpgl.zzsfpxx.verify;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.fop.fo.properties.EnumNumber;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.ywgl.zzsfpgl.zzsfpxx.entity.Zzsfpxx;
import org.cmms.modules.ywgl.zzsfpgl.zzsfpxx.service.IZzsfpxxService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ZzsfpxxImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IZzsfpxxService zzsfpxxService;
    @Autowired
    private IHrBasOrganizationService hrBasOrganizationService;



    @Override
    public String[] getNeedVerifyFields() {
        return null;
    }

    @Override
    public void setNeedVerifyFields(String[] var1) {

    }

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object var1, String var2, Object var3) {
        Object var=var1;
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        Zzsfpxx zzsfpxx = (Zzsfpxx) var1;
        String jgdm = (String)var3;
        zzsfpxx.setJgdm(jgdm);

        QueryWrapper<Zzsfpxx> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fphm",zzsfpxx.getFphm());
        queryWrapper.eq("hydm",zzsfpxx.getHydm());
        Zzsfpxx zzsfpxx1 = zzsfpxxService.getOne(queryWrapper,false);
        if (zzsfpxx1 != null){
            result.setSuccess(false);
            result.setMsg("导入失败，该发票信息已被用户["+zzsfpxx1.getLrr()+"]录入！");
            return result;
        }

        QueryWrapper<HrBasOrganization> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("ywjgdm",zzsfpxx.getJgdm());
        HrBasOrganization one = hrBasOrganizationService.getOne(queryWrapper1,false);
        if (one == null){
            result.setSuccess(false);
            result.setMsg("导入失败，机构代码在组织机构中不存在，请核实！");
            return result;
        }
        return result;
    }
}
