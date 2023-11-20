package org.cmms.modules.report.sgtzgl.dgckzhyeb.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.report.sgtzgl.cwbbzcfzb.entity.SgtzglCwbbzcfzb;
import org.cmms.modules.report.sgtzgl.cwbbzcfzb.service.ISgtzglCwbbzcfzbService;
import org.cmms.modules.report.sgtzgl.dgckzhyeb.entity.SgtzglDgckzhyeb;
import org.cmms.modules.report.sgtzgl.dgckzhyeb.service.ISgtzglDgckzhyebService;
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
public class DgckzhyebImportVerify implements IExcelVerifyHandler {

    @Autowired
    private ISgtzglDgckzhyebService sgtzglDgckzhyebService;

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
        SgtzglDgckzhyeb form = (SgtzglDgckzhyeb) var1;
        String bz = (String)var3;
        form.setBz(bz);

        /*QueryWrapper<SgtzglDgckzhyeb> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("khrq",form.getKhrq());
        queryWrapper.eq("zjhm",form.getZjhm());
        queryWrapper.eq("khjg",form.getKhjg());
        SgtzglDgckzhyeb cwbbzcfzb = sgtzglDgckzhyebService.getOne(queryWrapper, false);
        if (cwbbzcfzb != null){
            result.setSuccess(false);
            result.setMsg("导入失败,请勿导入重复数据！");
            return result;
        }*/
        return result;

    }
}
