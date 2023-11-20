package org.cmms.modules.report.sgtzgl.hnkjxzxqymd.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.checkerframework.checker.units.qual.C;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.report.sgtzgl.gmgyqymd.entity.SgztGmgyqymd;
import org.cmms.modules.report.sgtzgl.hnkjxzxqymd.controller.SgztHnkjxzxqymdController;
import org.cmms.modules.report.sgtzgl.hnkjxzxqymd.entity.SgztHnkjxzxqymd;
import org.cmms.modules.report.sgtzgl.hnkjxzxqymd.service.impl.SgztHnkjxzxqymdServiceImpl;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 龚辉
 * @date 2022/8/27 0:27 周六
 */
@Component
public class HnkjxzxqymdImportVerify implements IExcelVerifyHandler {

    @Autowired
    private SgztHnkjxzxqymdServiceImpl sgztHnkjxzxqymdService;

    @Override
    public String[] getNeedVerifyFields() {
        return null;
    }

    @Override
    public void setNeedVerifyFields(String[] strings) {

    }


    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object var1, String var2, Object var3) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        System.out.println("var1" + var1);
        System.out.println("var3" + var3);
        SgztHnkjxzxqymd form = (SgztHnkjxzxqymd) var1;
        String sfgq = (String)var3;
        form.setSfgq(sfgq);
    /*    QueryWrapper<SgztHnkjxzxqymd> queryWrapper = new QueryWrapper<>();
        SgztHnkjxzxqymd qkmb1 = sgztHnkjxzxqymdService.getOne(queryWrapper);
        sgztHnkjxzxqymdService.update(qkmb1, queryWrapper);
        result.setSuccess(false);
        result.setMsg("导入成功！");*/
        return result;
    }
}
