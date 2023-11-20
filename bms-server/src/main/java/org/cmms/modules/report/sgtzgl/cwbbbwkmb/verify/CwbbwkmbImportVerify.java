package org.cmms.modules.report.sgtzgl.cwbbbwkmb.verify;

import cn.hutool.http.server.HttpServerRequest;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.report.sgtzgl.cwbbbwkmb.entity.SgtzCwbbbwkmb;
import org.cmms.modules.report.sgtzgl.cwbbbwkmb.service.ISgtzCwbbbwkmbService;
import org.cmms.modules.report.sgtzgl.cwbbzcfzb.entity.SgtzglCwbbzcfzb;
import org.cmms.modules.report.sgtzgl.cwbbzcfzb.service.ISgtzglCwbbzcfzbService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author
 * @date 2022/8/26 23:19 周五
 */
@Component
public class CwbbwkmbImportVerify implements IExcelVerifyHandler {

    @Autowired
    private ISgtzCwbbbwkmbService sgtzCwbbbwkmbService;

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
        SgtzCwbbbwkmb form = (SgtzCwbbbwkmb) var1;
        System.out.println(form);
        BigDecimal qmdfye = (BigDecimal)var3;
        form.setQmdfye(qmdfye);


        return result;

    }
}
