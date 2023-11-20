package org.cmms.modules.jylrhs.csgl.dxfyjz.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.jylrhs.csgl.dxfyjz.entity.JylrhsDxfyjzVO;
import org.cmms.modules.jylrhs.csgl.dxfyjz.service.IJylrhsDxfyjzService;
import org.cmms.modules.jylrhs.csgl.kmsz.entity.JylrhsKmsz;
import org.cmms.modules.jylrhs.csgl.kmsz.service.IJylrhsKmszService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 经营利润核算（单项费用记账）导入验证类
 *
 * @author penghr
 * @date 2023年6月8日
 */
@Slf4j
@Component
public class JylrhsDxfyjzImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IJylrhsDxfyjzService dxfyjzService;
    @Autowired
    private IHrBasOrganizationService hrBasOrganizationService;
    @Autowired
    private IJylrhsKmszService kmszService;

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
        JylrhsDxfyjzVO form = (JylrhsDxfyjzVO) var1;
        if (form != null) {
            QueryWrapper<HrBasOrganization> orgQueryWrapper = new QueryWrapper<>();
            orgQueryWrapper.eq("ywjgdm",form.getJgdm());
            HrBasOrganization org = hrBasOrganizationService.getOne(orgQueryWrapper,false);
            if (org == null) {
                result.setSuccess(false);
                result.setMsg("该业务机构在组织机构中不存在！");
                return result;
            }
            QueryWrapper<JylrhsKmsz> kmszQueryWrapper = new QueryWrapper<>();
            kmszQueryWrapper.eq("subject_no1","6601");
            kmszQueryWrapper.eq("subject_no2",var3);
            JylrhsKmsz kmsz = kmszService.getOne(kmszQueryWrapper,false);
            if (kmsz == null) {
                result.setSuccess(false);
                result.setMsg("列账子目在科目设置中6601科目下辖二级科目中不存在！");
                return result;
            }
        }
        return result;
    }
}
