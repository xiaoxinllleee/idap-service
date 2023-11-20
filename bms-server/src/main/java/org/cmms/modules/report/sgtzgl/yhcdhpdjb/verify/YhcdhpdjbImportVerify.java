package org.cmms.modules.report.sgtzgl.yhcdhpdjb.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.report.sgtzgl.dkhxdjb.entity.SgtzglDkhxdjb;
import org.cmms.modules.report.sgtzgl.dkhxdjb.service.ISgtzglDkhxdjbService;
import org.cmms.modules.report.sgtzgl.yhcdhpdjb.entity.SgtzglYhcdhpdjb;
import org.cmms.modules.report.sgtzgl.yhcdhpdjb.service.ISgtzglYhcdhpdjbService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author
 * @date 2022/8/26 23:19 周五
 */
@Component
public class YhcdhpdjbImportVerify implements IExcelVerifyHandler {

    @Autowired
    private ISgtzglYhcdhpdjbService sgtzglYhcdhpdjbService;

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
        SgtzglYhcdhpdjb form = (SgtzglYhcdhpdjb) var1;
        String ywbh = (String)var3;
        form.setYwbh(ywbh);

       /* QueryWrapper<SgtzglYhcdhpdjb> queryWrapper = new QueryWrapper<>();
        SgtzglYhcdhpdjb cwbbzcfzb = sgtzglYhcdhpdjbService.getOne(queryWrapper, false);
        if (cwbbzcfzb != null){
            result.setSuccess(false);
            result.setMsg("导入失败,已存在该账号！");
            return result;
        }*/
        return result;

    }
}
