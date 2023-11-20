package org.cmms.modules.xddagl.dkdagl.xfdkhtsjgl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.xddagl.dkdagl.xfdkhtsjgl.entity.Xfdkhtsjgl;
import org.cmms.modules.xddagl.dkdagl.xfdkhtsjgl.service.IXfdkhtsjglService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class XfdkhtsjglImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IXfdkhtsjglService xfdkhtsjglService;



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
        Xfdkhtsjgl xfdkhtsjgl = (Xfdkhtsjgl) var1;
        String hth = (String)var3;
        if (StringUtils.isEmpty(hth)){
            result.setSuccess(false);
            result.setMsg("合同号不能为空，请重新输入！");
            return result;
        }
        QueryWrapper<Xfdkhtsjgl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hth",hth);
        Xfdkhtsjgl xfdkhtsjgl1 = xfdkhtsjglService.getOne(queryWrapper);
        if (xfdkhtsjgl1 != null){
            if (!StringUtils.isEmpty(xfdkhtsjgl.getDkzrr())){
                xfdkhtsjgl1.setDkzrr(xfdkhtsjgl.getDkzrr());
            }
            if (!StringUtils.isEmpty(xfdkhtsjgl.getDkpz())){
                xfdkhtsjgl1.setDkpz(xfdkhtsjgl.getDkpz());
            }
            if (!StringUtils.isEmpty(xfdkhtsjgl.getLxdh())){
                xfdkhtsjgl1.setLxdh(xfdkhtsjgl.getLxdh());
            }
            if (!StringUtils.isEmpty(xfdkhtsjgl.getLxdz())){
                xfdkhtsjgl1.setLxdz(xfdkhtsjgl.getLxdz());
            }
            xfdkhtsjglService.update(xfdkhtsjgl1,queryWrapper);
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
