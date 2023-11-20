package org.cmms.modules.jylrhs.csgl.jgkmsz.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.checkerframework.checker.units.qual.A;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.jylrhs.csgl.jgkmsz.entity.JylrhsKmszJg;
import org.cmms.modules.jylrhs.csgl.jgkmsz.entity.JylrhsKmszJgVO;
import org.cmms.modules.jylrhs.csgl.jgkmsz.service.IJylrhsKmszJgService;
import org.cmms.modules.jylrhs.csgl.kmsz.entity.JylrhsKmsz;
import org.cmms.modules.jylrhs.csgl.kmsz.service.IJylrhsKmszService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 经营利润核算（机构科目设置）导入验证类
 *
 * @author penghr
 * @date 2023年6月8日
 */
@Slf4j
@Component
public class JylrhsJgkmszImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IHrBasOrganizationService hrBasOrganizationService;
    @Autowired
    private IJylrhsKmszService kmszService;
    @Autowired
    private IJylrhsKmszJgService kmszJgService;

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
        //LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        JylrhsKmszJgVO form = (JylrhsKmszJgVO) var1;
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
            kmszQueryWrapper.eq("subject_no1",form.getSubjectNo1());
            kmszQueryWrapper.eq("subject_no2",form.getSubjectNo2());
            JylrhsKmsz kmsz = kmszService.getOne(kmszQueryWrapper,false);
            if (kmsz == null) {
                result.setSuccess(false);
                result.setMsg("一级科目与二级科目在科目设置中不存在！");
                return result;
            }
            QueryWrapper<JylrhsKmszJg> kmszJgQueryWrapper = new QueryWrapper<>();
            kmszJgQueryWrapper.eq("jgdm",form.getJgdm());
            kmszJgQueryWrapper.eq("subject_no1",form.getSubjectNo1());
            kmszJgQueryWrapper.eq("subject_no2",form.getSubjectNo2());
            JylrhsKmszJg kmszJg = kmszJgService.getOne(kmszJgQueryWrapper,false);
            if (kmszJg != null) {
                result.setSuccess(false);
                result.setMsg("该机构科目设置已存在，请核实！");
                return result;
            }
        }
        return result;
    }
}
