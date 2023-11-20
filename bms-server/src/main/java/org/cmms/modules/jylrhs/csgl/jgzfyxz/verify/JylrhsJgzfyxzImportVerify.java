package org.cmms.modules.jylrhs.csgl.jgzfyxz.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.jylrhs.csgl.jgzfyxz.entity.JylrhsZfyxzJg;
import org.cmms.modules.jylrhs.csgl.jgzfyxz.entity.JylrhsZfyxzJgVO;
import org.cmms.modules.jylrhs.csgl.jgzfyxz.service.IJylrhsZfyxzJgService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 经营利润核算（机构费用上限）导入验证类
 *
 * @author penghr
 * @date 2023年6月8日
 */
@Slf4j
@Component
public class JylrhsJgzfyxzImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IHrBasOrganizationService hrBasOrganizationService;
    @Autowired
    private IJylrhsZfyxzJgService jylrhsZfyxzJgService;

    @Override
    public String[] getNeedVerifyFields() {
        return null;
    }

    @Override
    public void setNeedVerifyFields(String[] strings) {
    }

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object var1, String var2, Object var3) {
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        JylrhsZfyxzJgVO form = (JylrhsZfyxzJgVO) var1;
        if (form != null) {
            QueryWrapper<HrBasOrganization> orgQueryWrapper = new QueryWrapper<>();
            orgQueryWrapper.eq("ywjgdm",form.getJgdm());
            HrBasOrganization org = hrBasOrganizationService.getOne(orgQueryWrapper,false);
            if (org == null) {
                result.setSuccess(false);
                result.setMsg("该业务机构在组织机构中不存在！");
                return result;
            }
            QueryWrapper<JylrhsZfyxzJg> zfyxzJgQueryWrapper = new QueryWrapper<>();
            zfyxzJgQueryWrapper.eq("jgdm",form.getJgdm());
            zfyxzJgQueryWrapper.eq("jznf",form.getJznf());
            JylrhsZfyxzJg zfyxzJg = jylrhsZfyxzJgService.getOne(zfyxzJgQueryWrapper,false);
            if (zfyxzJg != null) {
                result.setSuccess(false);
                result.setMsg("该机构费用上限已存在，请核实！");
                return result;
            }
        }
        return result;
    }
}
