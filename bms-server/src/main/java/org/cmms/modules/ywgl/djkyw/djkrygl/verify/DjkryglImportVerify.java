package org.cmms.modules.ywgl.djkyw.djkrygl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import org.cmms.modules.hr.yggl.ygrggl.service.IHrBasStaffPostService;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.cmms.modules.sjxf.hxxt.dkzdkb.entity.Dkzdkb;
import org.cmms.modules.sjxf.hxxt.dkzdkb.service.IDkzdkbService;
import org.cmms.modules.ywgl.ckyw.ckzhgl.entity.Ckzhgl;
import org.cmms.modules.ywgl.ckyw.ckzhgl.service.ICkzhglService;
import org.cmms.modules.ywgl.djkyw.djkrygl.entity.Djkrygl;
import org.cmms.modules.ywgl.djkyw.djkrygl.service.IDjkryglService;
import org.cmms.modules.ywgl.dkyw.dkzhzy.entity.Dkzhzy;
import org.cmms.modules.ywgl.dkyw.dkzhzy.service.IDkzhzyService;
import org.cmms.modules.ywgl.dkyw.lsdksjgl.entity.Lsdksjgl;
import org.cmms.modules.ywgl.dkyw.lsdksjgl.service.ILsdksjglService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class DjkryglImportVerify implements IExcelVerifyHandler {

    @Autowired
    private IDjkryglService djkryglService;
    @Autowired
    private IHrBasStaffService hrBasStaffService;

    @Override
    public String[] getNeedVerifyFields() {
        return null;
    }

    @Override
    public void setNeedVerifyFields(String[] var1) {}

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
        Djkrygl table1 = (Djkrygl) var1;
        String yggh = (String) var3;
        QueryWrapper<HrBasStaff> queryWrapper = new QueryWrapper();
        queryWrapper.eq("yggh", yggh);
        HrBasStaff staff = hrBasStaffService.getOne(queryWrapper);
        if (staff == null) {
            result.setSuccess(false);
            result.setMsg("员工信息不存在!");
            return result;
        }
        QueryWrapper<Djkrygl> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("yggh", yggh);
        queryWrapper1.eq("tgrybh", table1.getTgrybh());
        Djkrygl one = djkryglService.getOne(queryWrapper1);
        if (one != null) {
            result.setSuccess(false);
            result.setMsg("数据已存在,跳过当前该条数据的导入!");
            return result;
        }
        table1.setYgxm(staff.getYgxm());
        table1.setLrbz(0);
        table1.setLrsj(new Timestamp(System.currentTimeMillis()));
        table1.setLrczy(sysUser.getUsername());
        return result;
    }
}
