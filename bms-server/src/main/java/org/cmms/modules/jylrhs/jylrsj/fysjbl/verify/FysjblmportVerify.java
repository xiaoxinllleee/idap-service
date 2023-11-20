package org.cmms.modules.jylrhs.jylrsj.fysjbl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.jylrhs.csgl.zbk.entity.JylrhsZbk;
import org.cmms.modules.jylrhs.csgl.zbk.service.IJylrhsZbkService;
import org.cmms.modules.jylrhs.jylrsj.fysjbl.entity.JylrhsFysjbl;
import org.cmms.modules.jylrhs.jylrsj.fysjbl.entity.JylrhsFysjblVO;
import org.cmms.modules.jylrhs.jylrsj.fysjbl.service.IJylrhsFysjblService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysRole;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysRoleService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 经营利润核算（费用数据）导入验证类
 *
 * @author penghr
 * @date 2023年6月8日
 */
@Slf4j
@Component
public class FysjblmportVerify implements IExcelVerifyHandler {
    @Autowired
    private IJylrhsFysjblService jylrhsFysjblService;
    @Autowired
    private IHrBasOrganizationService hrBasOrganizationService;
    @Autowired
    private IJylrhsZbkService jylrhsZbkService;
    @Autowired
    private ISysRoleService sysRoleService;

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
        JylrhsFysjblVO form = (JylrhsFysjblVO) var1;
        if (form != null) {
            QueryWrapper<HrBasOrganization> orgQueryWrapper = new QueryWrapper<>();
            orgQueryWrapper.eq("ywjgdm",form.getJgdm());
            HrBasOrganization org = hrBasOrganizationService.getOne(orgQueryWrapper,false);
            if (org == null) {
                result.setSuccess(false);
                result.setMsg("该业务机构在组织机构中不存在！");
                return result;
            }
            QueryWrapper<JylrhsZbk> zbkQueryWrapper = new QueryWrapper<>();
            zbkQueryWrapper.eq("zbid", form.getZbid());
            JylrhsZbk zbk = jylrhsZbkService.getOne(zbkQueryWrapper,false);
            if (zbk == null) {
                result.setSuccess(false);
                result.setMsg("该指标ID在指标库中不存在！");
                return result;
            }
            QueryWrapper<JylrhsFysjbl> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("fiscal_date",form.getFiscalDate());
            queryWrapper.eq("jgdm",form.getJgdm());
            queryWrapper.eq("zbid",form.getZbid());
            JylrhsFysjbl record = jylrhsFysjblService.getOne(queryWrapper,false);
            if (record != null) {
                result.setSuccess(false);
                result.setMsg("该费用数据补录已存在，请核实！");
                return result;
            }
        }
        return result;
    }
}
