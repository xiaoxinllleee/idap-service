package org.cmms.modules.khlc.khfagl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasPost;
import org.cmms.modules.hr.zzgl.gwxxgl.service.IHrBasPostService;
import org.cmms.modules.hr.zzgl.zzgwgl.service.IHrBasOrganPostService;
import org.cmms.modules.khlc.jczbgl.entity.ErpBasZbk;
import org.cmms.modules.khlc.jczbgl.service.IErpBasZbkService;
import org.cmms.modules.khlc.khfagl.entity.ErpAssessAljc;
import org.cmms.modules.khlc.khfagl.service.IErpAssessAljcService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class KhzbAljcImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IErpAssessAljcService erpAssessAljcService;
    @Autowired
    private IHrBasOrganizationService hrBasOrganizationService;
    @Autowired
    private IErpBasZbkService erpBasZbkService;
    @Autowired
    private IHrBasPostService hrBasPostService;



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
        ErpAssessAljc table1 = (ErpAssessAljc) var1;
        BigDecimal rwwdj = (BigDecimal) var3;
        table1.setRwwdj(rwwdj);
        QueryWrapper<HrBasOrganization> organizationQueryWrapper = new QueryWrapper<>();
        organizationQueryWrapper.eq("zzbz",table1.getZzbz());
        HrBasOrganization org = hrBasOrganizationService.getOne(organizationQueryWrapper);
        if (org == null){
            if (org == null){
                result.setSuccess(false);
                result.setMsg("不存在该组织！");
                return result;
            }
        }

        QueryWrapper<HrBasPost> wrapper = new QueryWrapper<>();
        wrapper.eq("gwbz",table1.getGwbz());
        HrBasPost staff_post = hrBasPostService.getOne(wrapper);
        if (staff_post == null){
            result.setSuccess(false);
            result.setMsg("不存在该岗位！");
            return result;
        }

        QueryWrapper<ErpBasZbk> zbkQueryWrapper = new QueryWrapper<>();
        zbkQueryWrapper.eq("zbid",table1.getZbid());
        ErpBasZbk zbk = erpBasZbkService.getOne(zbkQueryWrapper);
        if (zbk == null){
            result.setSuccess(false);
            result.setMsg("指标标识不存在！");
            return result;
        }

        QueryWrapper<ErpAssessAljc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("scheme_id",table1.getSchemeId());
        queryWrapper.eq("zzbz",table1.getZzbz());
        queryWrapper.eq("gwbz",table1.getGwbz());
        queryWrapper.eq("zbid",table1.getZbid());
        ErpAssessAljc check = erpAssessAljcService.getOne(queryWrapper);
        if (check != null){
            erpAssessAljcService.remove(queryWrapper);
        }

        return result;

    }
}
