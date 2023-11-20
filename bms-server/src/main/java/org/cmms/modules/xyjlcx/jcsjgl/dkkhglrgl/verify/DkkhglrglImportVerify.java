package org.cmms.modules.xyjlcx.jcsjgl.dkkhglrgl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.xyjlcx.jcsjgl.dkkhglrgl.entity.Dkkhglrgl;
import org.cmms.modules.xyjlcx.jcsjgl.dkkhglrgl.service.IDkkhglrglService;
import org.cmms.modules.ywgl.cdkfx.dkqxsz.entity.ErpBasDkqxsz;
import org.cmms.modules.ywgl.cdkfx.dkqxsz.service.IErpBasDkqxszService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class DkkhglrglImportVerify implements IExcelVerifyHandler {

    @Autowired
    private IDkkhglrglService dkkhglrglService;
    @Autowired
    private ISysDictService iSysDictService;
    @Override
    public String[] getNeedVerifyFields() {
        return null;
    }

    @Override
    public void setNeedVerifyFields(String[] var1) {

    }

    /**
     *
     * @param var1 实体对象，包含设置interHandler的字段之前的所有字段值
     * @param var2 设置interHandler的字段名称
     * @param var3 设置interHandler的字段值
     * @return
     */

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object var1, String var2, Object var3) {
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        Dkkhglrgl table1 =(Dkkhglrgl)var1;
        String glrlxdh= (String) var3;
        table1.setGlrlxdh(glrlxdh);
        QueryWrapper queryWrapper =new QueryWrapper();
        queryWrapper.eq("jkrzjhm",table1.getJkrzjhm());
        queryWrapper.eq("glrzjhm",table1.getGlrzjhm());
        Dkkhglrgl check = dkkhglrglService.getOne(queryWrapper);

        if(check!=null){
            result.setSuccess(false);
            result.setMsg("该关联人证件号码已经存在关联，请勿重复关联！");
            return result;
        }
        table1.setLrbz(0);
        table1.setLrsj(new Timestamp(System.currentTimeMillis()));
        table1.setLrr(sysUser.getUsername());

        return result;
    }


}
