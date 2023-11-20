package org.cmms.modules.ywgl.cdkfx.khjlxzblkk.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.ywgl.cdkfx.dkqxsz.entity.ErpBasDkqxsz;
import org.cmms.modules.ywgl.cdkfx.dkqxsz.service.IErpBasDkqxszService;
import org.cmms.modules.ywgl.cdkfx.khjlxzblkk.entity.ErpJxkhKhjlxzblkkT;
import org.cmms.modules.ywgl.cdkfx.khjlxzblkk.service.IErpJxkhKhjlxzblkkTService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Component
public class KhjlxzblkkImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IErpJxkhKhjlxzblkkTService erpJxkhKhjlxzblkkTService;
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
        ErpJxkhKhjlxzblkkT table1 =(ErpJxkhKhjlxzblkkT)var1;
        BigDecimal bqsk= (BigDecimal) var3;
        table1.setBqsk(bqsk);
        QueryWrapper queryWrapper =new QueryWrapper();
        queryWrapper.eq("jgdm",table1.getJgdm());
        queryWrapper.eq("yggh",table1.getYggh());
        queryWrapper.eq("khyf",table1.getKhyf());
        queryWrapper.eq("khjlbz",table1.getKhjlbz());
        ErpJxkhKhjlxzblkkT check = erpJxkhKhjlxzblkkTService.getOne(queryWrapper);
        if(check==null){
            result.setSuccess(false);
            result.setMsg("导入失败，信息不存在！！");
            return result;
        }

        check.setBqsk(table1.getBqsk());
        check.setLrsj(new Timestamp(System.currentTimeMillis()));
        check.setLrbz(2);
        check.setLrczy(sysUser.getUsername());
        erpJxkhKhjlxzblkkTService.update(check,queryWrapper);

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
