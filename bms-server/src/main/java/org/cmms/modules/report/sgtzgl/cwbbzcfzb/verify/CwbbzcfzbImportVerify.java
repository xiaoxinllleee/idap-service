package org.cmms.modules.report.sgtzgl.cwbbzcfzb.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.report.sgtzgl.cwbblrb.service.impl.SgztCwbblrbServiceImpl;
import org.cmms.modules.report.sgtzgl.cwbbzcfzb.entity.SgtzglCwbbzcfzb;
import org.cmms.modules.report.sgtzgl.cwbbzcfzb.service.ISgtzglCwbbzcfzbService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author
 * @date 2022/8/26 23:19 周五
 */
@Component
public class CwbbzcfzbImportVerify implements IExcelVerifyHandler {

    @Autowired
    private ISgtzglCwbbzcfzbService sgtzglCwbbzcfzbService;

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
        SgtzglCwbbzcfzb form = (SgtzglCwbbzcfzb) var1;
        BigDecimal fzqmye = (BigDecimal)var3;
        form.setFzqmye(fzqmye);

        QueryWrapper<SgtzglCwbbzcfzb> queryWrapper = new QueryWrapper<>();
       /* queryWrapper.eq("zc",form.getZc());
        SgtzglCwbbzcfzb cwbbzcfzb = sgtzglCwbbzcfzbService.getOne(queryWrapper, false);
        if (cwbbzcfzb != null){
            result.setSuccess(false);
            result.setMsg("导入失败,请勿导入重复数据！");
            return result;
        }*/
        return result;

    }
}
