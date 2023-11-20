package org.cmms.modules.dklldj.csszgl.fdcsdzb.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;

import org.cmms.common.util.DateUtil;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.dklldj.csszgl.fdcsdzb.entity.Fdcsdzb;
import org.cmms.modules.dklldj.csszgl.fdcsdzb.service.IFdcsdzbService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
public class FdcsdzbVerify implements IExcelVerifyHandler {
    @Autowired
    private IFdcsdzbService fdcsdzbService;
    @Autowired
    IDictValueQuery iDictValueQuery;

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
        Fdcsdzb form = (Fdcsdzb) var1;
        BigDecimal dyjdbp = (BigDecimal) var3;
        form.setDyjdbp(dyjdbp);
        System.out.println(var1+"var1");
        System.out.println(var2+"var2");
        System.out.println(var3+"var3");

        QueryWrapper<Fdcsdzb> queryWrapper = new QueryWrapper<Fdcsdzb>();
        queryWrapper.eq("djdf",form.getDjdf());
        queryWrapper.eq("dkqx",form.getDkqx());
        queryWrapper.eq("dksxje_begin",form.getDksxjeBegin());
        queryWrapper.eq("dksxje_end",form.getDksxjeEnd());
        queryWrapper.eq("dyfdfd",form.getDyfdfd());
        queryWrapper.eq("dyjdbp",form.getDyjdbp());
//        Fdcsdzb check = fdcsdzbService.getOne(queryWrapper);
        List<Fdcsdzb> check = fdcsdzbService.list(queryWrapper);
        if (check != null){
            fdcsdzbService.remove(queryWrapper);
            fdcsdzbService.update(form,queryWrapper);
        }
        form.setId(String.valueOf(iDictValueQuery.getSeqRateZxlldjbDjidNextval("SEQ_PUBLIC_ID.nextval")));
        form.setLrbz(0);
        form.setLrr(sysUser.getUsername());
        form.setLrsj(new Date());
        return result;


        }
    }





