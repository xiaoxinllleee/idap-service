package org.cmms.modules.ywgl.ywl.ywltz.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import org.cmms.modules.hr.yggl.ygrggl.service.IHrBasStaffPostService;
import org.cmms.modules.ywgl.ywl.ywltz.entity.Ywltz;
import org.cmms.modules.ywgl.ywl.ywltz.service.IYwltzService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class YwltzImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IYwltzService ywltzService;
    @Autowired
    private IHrBasStaffPostService hrBasStaffPostService;



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
        Ywltz ywltz = (Ywltz) var1;
        Date tzyf = (Date) var3;

        QueryWrapper<HrBasStaffPost> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("zzbz",ywltz.getZzbz());
        queryWrapper.eq("gwbz",ywltz.getGwbz());
        queryWrapper.eq("yggh",ywltz.getYggh());
        HrBasStaffPost one = hrBasStaffPostService.getOne(queryWrapper);
        if (one == null){
            result.setSuccess(false);
            result.setMsg("此组织下不存在此岗位信息或不存在此员工信息！");
            return result;
        }
        QueryWrapper<Ywltz> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("zzbz",ywltz.getZzbz());
        queryWrapper1.eq("gwbz",ywltz.getGwbz());
        queryWrapper1.eq("yggh",ywltz.getYggh());
        queryWrapper1.eq("tzyf",tzyf);
        Ywltz ywltz1 = ywltzService.getOne(queryWrapper1);
        if (ywltz1 != null){
            ywltzService.remove(queryWrapper1);
        }

        return result;
    }
}
