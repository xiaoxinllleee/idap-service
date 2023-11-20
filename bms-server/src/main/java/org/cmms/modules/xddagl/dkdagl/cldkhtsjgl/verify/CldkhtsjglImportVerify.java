package org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.entity.Cldkhtsjgl;
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.service.ICldkhtsjglService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class CldkhtsjglImportVerify implements IExcelVerifyHandler {
    @Autowired
    private ICldkhtsjglService cldkhtsjglService;



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
        Cldkhtsjgl cldkhtsjgl = (Cldkhtsjgl) var1;
        String hth = (String)var3;
        if (StringUtils.isEmpty(hth)){
            result.setSuccess(false);
            result.setMsg("合同号不能为空，请重新输入！");
            return result;
        }
        QueryWrapper<Cldkhtsjgl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hth",hth);
        Cldkhtsjgl cldkhtsjgl1 = cldkhtsjglService.getOne(queryWrapper);
        if (cldkhtsjgl1 != null){
            if (!StringUtils.isEmpty(cldkhtsjgl.getDkzrr())){
                cldkhtsjgl1.setDkzrr(cldkhtsjgl.getDkzrr());
            }
            if (!StringUtils.isEmpty(cldkhtsjgl.getDkpz())){
                cldkhtsjgl1.setDkpz(cldkhtsjgl.getDkpz());
            }
            if (!StringUtils.isEmpty(cldkhtsjgl.getLxdh())){
                cldkhtsjgl1.setLxdh(cldkhtsjgl.getLxdh());
            }
            if (!StringUtils.isEmpty(cldkhtsjgl.getLxdz())){
                cldkhtsjgl1.setLxdz(cldkhtsjgl.getLxdz());
            }
            cldkhtsjglService.update(cldkhtsjgl1,queryWrapper);
            result.setSuccess(false);
            result.setMsg("导入成功！");
            return result;
        }else {
            result.setSuccess(false);
            result.setMsg("没找到该合同数据，导入失败！");
            return result;
        }
    }
}
