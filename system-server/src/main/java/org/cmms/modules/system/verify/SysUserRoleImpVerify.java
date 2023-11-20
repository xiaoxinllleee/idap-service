package org.cmms.modules.system.verify;

import cn.hutool.core.collection.CollUtil;
import org.cmms.common.constant.DictConstant;
import org.cmms.common.system.vo.DictModel;
import org.cmms.common.utils.ExcelVerifyUtil;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.system.vo.SysRoleExp;
import org.cmms.modules.system.vo.SysUserRoleExp;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Date 2022/1/15
 * @Created by eran
 */
@Component
public class SysUserRoleImpVerify implements IExcelVerifyHandler {
    @Autowired
    ISysDictService sysDictService;
    @Override
    public String[] getNeedVerifyFields() {
        return new String[0];
    }

    @Override
    public void setNeedVerifyFields(String[] arr) {

    }

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object obj, String name, Object value) {
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        if (value != null){
            String toValue = value.toString();
            if (name.equals("账号")){
                List<DictModel> users = sysDictService.queryTableDictItemsByCode(DictConstant.SYS_BAS_USER, "userid", "username");
                if (CollUtil.isNotEmpty(users)){
                    if (!ExcelVerifyUtil.isDictItemValue(toValue,users)){
                        result.setSuccess(false);
                        result.setMsg(DictConstant.USER_NAME_IS_NOT_EXIST);
                        return result;
                    }
                }
            }

            if (name.equals("角色名")){
                List<DictModel> roles = sysDictService.queryTableDictItemsByCode(DictConstant.SYS_ROLE, "role_code", "role_name");
                if (CollUtil.isNotEmpty(roles)){
                    if (!ExcelVerifyUtil.isDictItemValue(toValue,roles)){
                        result.setSuccess(false);
                        result.setMsg(DictConstant.DICT_ROLE_IS_NOT_EXIST);
                        return result;
                    }
                }
            }
        }
        return result;
    }
}
