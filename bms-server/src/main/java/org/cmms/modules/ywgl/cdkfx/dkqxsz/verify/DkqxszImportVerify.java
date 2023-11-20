package org.cmms.modules.ywgl.cdkfx.dkqxsz.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.ywgl.cdkfx.dkqxsz.entity.ErpBasDkqxsz;
import org.cmms.modules.ywgl.cdkfx.dkqxsz.service.IErpBasDkqxszService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class DkqxszImportVerify implements IExcelVerifyHandler {

    @Autowired
    private IErpBasDkqxszService erpBasDkqxszService;
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
        ErpBasDkqxsz table1 =(ErpBasDkqxsz)var1;
        Integer yxbz= (Integer) var3;
        table1.setYxbz(yxbz);


        QueryWrapper queryWrapper =new QueryWrapper();
        queryWrapper.eq("zzbz",table1.getZzbz());
        ErpBasDkqxsz check2 = erpBasDkqxszService.getOne(queryWrapper);

        if (check2!=null) {
            result.setSuccess(false);
            result.setMsg("该机构贷款权限已设置，请核对！");
            return result;
        }

        String ywjgdm= iSysDictService.queryTableDictTextByKey("HR_BAS_ORGANIZATION","YWJGDM","ZZBZ",table1.getZzbz());
        if(ywjgdm!=null){
            table1.setYwjgdm(ywjgdm);
        }

        table1.setLrbz(1);
        table1.setLrsj(new Timestamp(System.currentTimeMillis()));
        table1.setLrr(sysUser.getUsername());
        return result;
    }

    /**
     * 推广人员编号格式化
     * @param tgh
     * @return
     */
    public static String retTgh(String tgh){
        if(tgh!=null&&!"".equals(tgh)){
            tgh = tgh.replace(".","") ;
            return !tgh.equals("")&&tgh.length()>1?tgh.substring(0,tgh.length()).trim():tgh.trim();
        }
        return  "" ;
    }
}
