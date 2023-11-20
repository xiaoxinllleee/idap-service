package org.cmms.modules.jylrhs.csgl.khsz.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.jylrhs.csgl.khsz.entity.JylrhsKhsz;
import org.cmms.modules.jylrhs.csgl.khsz.entity.JylrhsKhszVO;
import org.cmms.modules.jylrhs.csgl.khsz.service.IJylrhsKhszService;
import org.cmms.modules.jylrhs.csgl.zbk.entity.JylrhsZbk;
import org.cmms.modules.jylrhs.csgl.zbk.entity.JylrhsZbkVO;
import org.cmms.modules.jylrhs.csgl.zbk.service.IJylrhsZbkService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 经营利润核算（考核设置）导入验证类
 *
 * @author penghr
 * @date 2023年6月8日
 */
@Slf4j
@Component
public class JylrhsKhszImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IJylrhsKhszService jylrhsKhszService;
    @Autowired
    private IJylrhsZbkService zbkService;
    @Autowired
    private IHrBasOrganizationService hrBasOrganizationService;

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
        JylrhsKhszVO form = (JylrhsKhszVO) var1;
        if (form != null) {
            QueryWrapper<HrBasOrganization> orgQueryWrapper = new QueryWrapper<>();
            orgQueryWrapper.eq("ywjgdm",form.getJgdm());
            HrBasOrganization org = hrBasOrganizationService.getOne(orgQueryWrapper,false);
            if (org == null) {
                result.setSuccess(false);
                result.setMsg("该业务机构在组织机构中不存在，请核实！");
                return result;
            }
            QueryWrapper<JylrhsZbk> zbkQueryWrapper = new QueryWrapper<>();
            zbkQueryWrapper.eq("zbid",form.getZbid());
            JylrhsZbk zbk = zbkService.getOne(zbkQueryWrapper,false);
            if (zbk == null) {
                result.setSuccess(false);
                result.setMsg("该指标在指标库中不存在，请核实！");
                return result;
            }
            QueryWrapper<JylrhsKhsz> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("jgdm",form.getJgdm());
            queryWrapper.eq("zbid",form.getZbid());
            queryWrapper.eq("khzq",form.getKhzq());
            JylrhsKhsz record = jylrhsKhszService.getOne(queryWrapper,false);
            if (record != null) {
                result.setSuccess(false);
                result.setMsg("该考核设置已存在，请核实！");
                return result;
            }
        }
        return result;
    }
}
