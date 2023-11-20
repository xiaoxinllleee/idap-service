package org.cmms.modules.ywgl.djkyw.djkwdgl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.ywgl.djkyw.djkrygl.entity.Djkrygl;
import org.cmms.modules.ywgl.djkyw.djkrygl.service.IDjkryglService;
import org.cmms.modules.ywgl.djkyw.djkwdgl.entity.Djkwdgl;
import org.cmms.modules.ywgl.djkyw.djkwdgl.service.IDjkwdglService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class DjkwdglImportVerify implements IExcelVerifyHandler {

    @Autowired
    private IDjkwdglService djkwdglService;
    @Autowired
    private IHrBasStaffService hrBasStaffService;
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
     * @param var1 实体对象，包含设置interHandler的字段之前的所有字段值
     * @param var2 设置interHandler的字段名称
     * @param var3 设置interHandler的字段值
     * @return
     */
    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object var1, String var2, Object var3) {
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        Djkwdgl table1 = (Djkwdgl) var1;
        String jgdm = (String) var3;
        if (StringUtils.isEmpty(jgdm)) {
            result.setSuccess(false);
            return result;
        }
        String jgmc = iSysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "ywjgdm", jgdm);
        if (jgmc == null || jgmc.equals("")) {
            result.setSuccess(false);
            result.setMsg("机构信息不存在");
            return result;
        }
        QueryWrapper<Djkwdgl> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("jgdm", jgdm);
        queryWrapper1.eq("tgjgbh", table1.getTgjgbh());
        Djkwdgl one = djkwdglService.getOne(queryWrapper1);
        if (one != null) {
            result.setSuccess(false);
            result.setMsg("数据已存在,跳过当前该条数据的导入!");
            return result;
        }
        table1.setLrbz(0);
        table1.setLrsj(new Timestamp(System.currentTimeMillis()));
        table1.setLrczy(sysUser.getUsername());
        return result;
    }
}
