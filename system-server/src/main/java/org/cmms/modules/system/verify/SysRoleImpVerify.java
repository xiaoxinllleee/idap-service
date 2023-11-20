package org.cmms.modules.system.verify;

import cn.hutool.core.collection.CollUtil;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.constant.DictConstant;
import org.cmms.common.handler.IFillRuleHandler;
import org.cmms.common.system.vo.DictModel;
import org.cmms.common.utils.ExcelVerifyUtil;
import org.cmms.modules.system.entity.SysRole;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.system.vo.SysRoleExp;
import org.cmms.modules.system.vo.SysUserExp;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2022/1/15
 * @Created by eran
 */
@Component
public class SysRoleImpVerify implements IExcelVerifyHandler {
    @Autowired
    ISysDictService sysDictService;
    @Override
    public String[] getNeedVerifyFields() {
        return null;
    }

    @Override
    public void setNeedVerifyFields(String[] arr) {

    }

    List<String> nameList = new ArrayList<>();
    List<String> codeList = new ArrayList<>();

    List<String> cgList = new ArrayList<>();

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object obj, String name, Object value) {
        System.out.println("SysRoleImpVerify===>");
        System.out.println(value);
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        if (value != null){
            List<DictModel> roles = sysDictService.queryTableDictItemsByCode(DictConstant.SYS_ROLE, "role_code", "role_name");

            //两个字段 先校验和表内的数据是否重复 再校验和导入数据是否重复
            if ("角色名".equals(name)){
                if (ExcelVerifyUtil.isDictItemValue(value.toString(),roles)){
                    result.setSuccess(false);
                    result.setMsg(DictConstant.DICT_ROLE_IS_EXIST);
                    return result;
                }
                cgList.add(value.toString());
            }
            if ("角色编码".equals(name)){
                if (ExcelVerifyUtil.isDictItemText(value.toString(),roles)){
                    result.setSuccess(false);
                    result.setMsg(DictConstant.DICT_ROLE_CODE_IS_EXIST);
                    return result;
                }
                cgList.add(value.toString());
            }

            if ("角色名".equals(name)){
                if (cgList.size() == 2){
                    nameList.add(cgList.get(0));
                    codeList.add(cgList.get(1));

                    cgList = new ArrayList<>();
                }
            }

            if (ExcelVerifyUtil.isRepeat(value.toString(),nameList)){
                result.setSuccess(false);
                result.setMsg(DictConstant.DICT_ROLE_REPEAT);
                return result;
            }

            if (ExcelVerifyUtil.isRepeat(value.toString(),codeList)){
                result.setSuccess(false);
                result.setMsg(DictConstant.DICT_ROLE_CODE_REPEAT);
                return result;
            }
        }
        return result;
    }

    public void reset(){
        nameList = new ArrayList<>();
        codeList = new ArrayList<>();
        cgList = new ArrayList<>();
    }
}
