package org.cmms.modules.xddagl.xdhc.xdhc01.verify;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.system.entity.SysRole;
import org.cmms.modules.system.entity.SysUserRole;
import org.cmms.modules.system.service.ISysUserRoleService;
import org.cmms.modules.xddagl.dkdagl.dkdahtsjglbc.entity.Dkdahtsjglbc;
import org.cmms.modules.xddagl.dkdagl.dkdahtsjglbc.service.IDkdahtsjglbcService;
import org.cmms.modules.xddagl.xdhc.xdhc01.entity.Xdhc01;
import org.cmms.modules.xddagl.xdhc.xdhc01.service.IXdhc01Service;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class Xdhc01Verify implements IExcelVerifyHandler {

    @Autowired
    private IXdhc01Service xdhc01Service;

    @Autowired
    ISysUserRoleService sysUserRoleService;

    @Override
    public String[] getNeedVerifyFields() {
        return new String[0];
    }

    @Override
    public void setNeedVerifyFields(String[] arr) {

    }

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object obj, String name, Object value) {
        System.out.println(obj);
        System.out.println(name);
        System.out.println(value);
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");

        Xdhc01 table1 = (Xdhc01) obj;
        String cyzrr = (String) value;
        table1.setCyzrr(cyzrr);
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        QueryWrapper<Xdhc01> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dkzh", table1.getDkzh());
        List<Xdhc01> check = xdhc01Service.list(queryWrapper);

        //判断是不是管理员
        LambdaQueryWrapper<SysUserRole> sysRoleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sysRoleLambdaQueryWrapper.eq(SysUserRole::getUserId, sysUser.getId());
        List<SysUserRole> list = sysUserRoleService.list(sysRoleLambdaQueryWrapper);
        for (int i = 0; i < check.size(); i++) {
            if (!list.get(0).getId().equals("1")) {
                Xdhc01 xdhc01 = check.get(i);
                if (xdhc01.getShzt() == 2) {
                    result.setSuccess(false);
                    result.setMsg("此信息已审核，不能修改");
                    return result;
                }
            }
        }
        if (check != null && check.size()>0) {
            if(!StringUtils.isEmpty(table1.getBlxcyy())){
                Xdhc01 xdhc01 = check.get(0);
                xdhc01.setBlxcyy(table1.getBlxcyy());
            }
            if(!StringUtils.isEmpty(table1.getZrjd())){
                Xdhc01 xdhc01 = check.get(0);
                xdhc01.setBlxcyy(table1.getZrjd());
            }
            if(!StringUtils.isEmpty(table1.getQsczcs())){
                Xdhc01 xdhc01 = check.get(0);
                xdhc01.setBlxcyy(table1.getQsczcs());
            }
            if(!StringUtils.isEmpty(table1.getQsczsx())){
                Xdhc01 xdhc01 = check.get(0);
                xdhc01.setBlxcyy(table1.getQsczsx());
            }
            if(!StringUtils.isEmpty(table1.getZyzrr())){
                Xdhc01 xdhc01 = check.get(0);
                xdhc01.setBlxcyy(table1.getZyzrr());
            }
            if(!StringUtils.isEmpty(table1.getQszrr())){
                Xdhc01 xdhc01 = check.get(0);
                xdhc01.setBlxcyy(table1.getQszrr());
            }
            if (list.get(0).getId().equals("1")) {
                Xdhc01 xdhc01 = check.get(0);
                xdhc01.setShzt(2);
            }
            xdhc01Service.update(table1, queryWrapper);
            result.setSuccess(false);
            result.setMsg("导入成功！");
            return result;
        } else {
            result.setSuccess(false);
            result.setMsg("没找到该帐号，导入失败！");
            return result;
        }

    }

}






