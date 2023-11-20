package org.cmms.modules.system.verify;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.cmms.common.constant.DictConstant;
import org.cmms.common.system.vo.DictModel;
import org.cmms.common.utils.ExcelVerifyUtil;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.cmms.modules.system.entity.SysRole;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.system.service.ISysUserService;
import org.cmms.modules.system.vo.SysUserExp;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2022/1/14
 * @Created by eran
 */
@Component
public class SysUserImpVerify implements IExcelVerifyHandler {
    @Autowired
    ISysDictService sysDictService;
    @Autowired
    ISysUserService sysUserService;
    @Autowired
    IHrBasStaffService hrBasStaffService;

    @Override
    public String[] getNeedVerifyFields() {
        return null;
    }

    @Override
    public void setNeedVerifyFields(String[] arr) {

    }

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object obj, String name, Object value) {
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        if (value != null){
            String toValue = value.toString().trim();
            if (name.equals("工号/账号")){
                LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper();
                lambdaQueryWrapper.eq(SysUser::getUsername,toValue);
                Long count = sysUserService.count(lambdaQueryWrapper);
                if (count > 0){
                    result.setSuccess(false);
                    result.setMsg(DictConstant.USER_NAME_IS_EXIST);
                    return result;
                }
            }

            if (name.equals("角色")){
                List<DictModel> roles = sysDictService.queryTableDictItemsByCode(DictConstant.SYS_ROLE, "id", "role_name");
                if (CollUtil.isNotEmpty(roles)){
                    if (!ExcelVerifyUtil.isDictItemValue(toValue,roles)){
                        result.setSuccess(false);
                        result.setMsg(DictConstant.DICT_ROLE_IS_NOT_EXIST);
                        return result;
                    }
                }
            }
            if (name.equals("权限组织(简称即可)")){
                List<DictModel> zzbzs = sysDictService.queryTableDictItemsByCode(DictConstant.HR_BAS_ORGANIZATION, "zzbz", "zzjc");
                if (CollUtil.isNotEmpty(zzbzs)){
                    if (!ExcelVerifyUtil.isDictItemValue(toValue,zzbzs)){
                        result.setSuccess(false);
                        result.setMsg(DictConstant.DICT_ORG_IS_NOT_EXIST);
                        return result;
                    }
                }
            }

        }
        return result;
    }
}
