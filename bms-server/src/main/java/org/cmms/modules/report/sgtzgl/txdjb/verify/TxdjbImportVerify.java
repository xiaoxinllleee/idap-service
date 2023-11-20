package org.cmms.modules.report.sgtzgl.txdjb.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.report.sgtzgl.qszlxxxcyqymd.entity.SgtzglQszlxxxcyqymd;
import org.cmms.modules.report.sgtzgl.qszlxxxcyqymd.service.ISgtzglQszlxxxcyqymdService;
import org.cmms.modules.report.sgtzgl.txdjb.entity.SgtzglTxdjb;
import org.cmms.modules.report.sgtzgl.txdjb.service.ISgtzglTxdjbService;
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
public class TxdjbImportVerify implements IExcelVerifyHandler {

    @Autowired
    private ISgtzglTxdjbService sgtzglTxdjbService;

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
        SgtzglTxdjb form = (SgtzglTxdjb) var1;
        String pjzt = (String)var3;
        form.setPjzt(pjzt);

     /*   QueryWrapper<SgtzglTxdjb> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pjhm",form.getPjhm());
        SgtzglTxdjb cwbbzcfzb = sgtzglTxdjbService.getOne(queryWrapper, false);
        if (cwbbzcfzb != null){
            result.setSuccess(false);
            result.setMsg("导入失败,请勿导入重复数据！");
            return result;
        }*/
        return result;

    }
}
