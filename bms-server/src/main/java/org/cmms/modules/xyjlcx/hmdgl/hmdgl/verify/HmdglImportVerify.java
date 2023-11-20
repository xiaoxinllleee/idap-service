package org.cmms.modules.xyjlcx.hmdgl.hmdgl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.xyjlcx.hmdgl.hmdgl.entity.Hmdgl;
import org.cmms.modules.xyjlcx.hmdgl.hmdgl.service.IHmdglService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
public class HmdglImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IHmdglService hmdglService;



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
        Hmdgl hmdgl = (Hmdgl) var1;
        Integer bljlxwms = (Integer) var3;

        QueryWrapper<Hmdgl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("zjhm",hmdgl.getZjhm());
        queryWrapper.eq("bljlxwms",bljlxwms);
        Hmdgl hmdgl1 = hmdglService.getOne(queryWrapper);
        if (hmdgl1 != null){
            if (hmdgl1.getYxbz()==1){
                result.setSuccess(false);
                result.setMsg("该客户已经被列入黑名单，请勿重复添加！");
                return result;
            }else {
                hmdgl1.setBljlxwms(bljlxwms);
                hmdgl1.setYxbz(1);
                hmdgl1.setDjrq(hmdgl.getDjrq());
                hmdgl1.setBlxwjsrq(hmdgl.getBlxwjsrq());
                hmdgl1.setBljlmcrq(null);
                hmdgl1.setQksm(hmdgl.getQksm());
                hmdgl1.setLrr(sysUser.getRealname());
                hmdgl1.setLrsj(new Date());
                hmdgl1.setLrbz(0);

                hmdglService.update(hmdgl1,queryWrapper);
                result.setSuccess(false);
                result.setMsg("记录已经存在，该条记录执行更新操作！");
                return result;
            }
        }else if (hmdgl1 == null){
            hmdgl.setYxbz(1);
            hmdgl.setLrbz(0);
            hmdgl.setLrsj(new Date());
            hmdgl.setLrr(sysUser.getRealname());
        }
        return result;
    }
}
