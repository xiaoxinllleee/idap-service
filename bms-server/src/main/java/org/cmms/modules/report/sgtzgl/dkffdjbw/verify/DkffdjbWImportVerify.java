package org.cmms.modules.report.sgtzgl.dkffdjbw.verify;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.report.sgtzgl.dkffdjb.entity.SgtzglDkffdjb;
import org.cmms.modules.report.sgtzgl.dkffdjb.service.ISgtzglDkffdjbService;
import org.cmms.modules.report.sgtzgl.dkffdjbw.entity.DkffdjbW;
import org.cmms.modules.report.sgtzgl.dkffdjbw.service.IDkffdjbWService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author
 * @date 2022/8/26 23:19 周五
 */
@Component
public class DkffdjbWImportVerify implements IExcelVerifyHandler {

    @Autowired
    private IDkffdjbWService dkffdjbWService;

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
        DkffdjbW form = (DkffdjbW) var1;

     /*   QueryWrapper<SgtzglDkffdjb> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("zjhm",form.getZjhm());
        SgtzglDkffdjb cwbbzcfzb = sgtzglDkffdjbService.getOne(queryWrapper, false);
        if (cwbbzcfzb != null){
            result.setSuccess(false);
            result.setMsg("导入失败,请勿导入重复数据！");
            return result;
        }*/
        return result;

    }
}
