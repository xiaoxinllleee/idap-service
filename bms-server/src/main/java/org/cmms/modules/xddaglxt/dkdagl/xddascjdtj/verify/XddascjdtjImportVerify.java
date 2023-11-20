package org.cmms.modules.xddaglxt.dkdagl.xddascjdtj.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import org.cmms.modules.hr.yggl.ygrggl.service.IHrBasStaffPostService;
import org.cmms.modules.sjxf.hxxt.dkzdkb.entity.Dkzdkb;
import org.cmms.modules.sjxf.hxxt.dkzdkb.service.IDkzdkbService;
import org.cmms.modules.sjxf.xdxt.dkywjjb.entity.Dkywjjb;
import org.cmms.modules.sjxf.xdxt.dkywjjb.service.IDkywjjbService;
import org.cmms.modules.sjxf.xdxt.txywyeb.entity.Txywyeb;
import org.cmms.modules.sjxf.xdxt.txywyeb.service.ITxywyebService;
import org.cmms.modules.xddaglxt.dkdagl.xddascjdtj.entity.Xddascjdtj;
import org.cmms.modules.xddaglxt.dkdagl.xddascjdtj.service.IXddascjdtjService;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.entity.Dksjzrgl;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.service.IDksjzrglService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Component
public class XddascjdtjImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IXddascjdtjService xddascjdtjService;

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
        Xddascjdtj table1 = (Xddascjdtj) var1;
        Long ysjs= (Long) var3;
        table1.setYsjs(ysjs);

        //检查是否存在手工修改过的数据
        QueryWrapper<Xddascjdtj> wrapper = new QueryWrapper<>();
        wrapper.eq("jgdm",table1.getJgdm());
        Xddascjdtj check = xddascjdtjService.getOne(wrapper);
        if (check != null){

            if (table1.getZdas() != null){
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("zdas",table1.getZdas());
                queryWrapper.eq("jgdm",check.getJgdm());
                xddascjdtjService.update(table1,queryWrapper);
            }
            if (table1.getYsjs() != null){
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("ysjs",table1.getYsjs());
                queryWrapper.eq("jgdm",check.getJgdm());
                xddascjdtjService.update(table1,queryWrapper);
            }
            result.setSuccess(false);
            result.setMsg("导入成功");
            return result;
        }else {
            result.setSuccess(false);
            result.setMsg("未找到此机构的档案信息！");
            return result;
        }
    }
}
