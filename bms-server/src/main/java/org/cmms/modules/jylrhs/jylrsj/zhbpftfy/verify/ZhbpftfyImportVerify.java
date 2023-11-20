package org.cmms.modules.jylrhs.jylrsj.zhbpftfy.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.cmms.modules.jylrhs.jylrsj.zhbpftfy.entity.JylrhsZhbpftfy;
import org.cmms.modules.jylrhs.jylrsj.zhbpftfy.entity.JylrhsZhbpftfyVO;
import org.cmms.modules.jylrhs.jylrsj.zhbpftfy.service.IJylrhsZhbpftfyService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 机关费用及支行报批分摊费用 导入验证类
 *
 * @author penghr
 * @date 2023年6月8日
 */
@Slf4j
@Component
public class ZhbpftfyImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IJylrhsZhbpftfyService service;
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
        JylrhsZhbpftfyVO form = (JylrhsZhbpftfyVO) var1;
        if (form != null) {
//            QueryWrapper<HrBasOrganization> orgQueryWrapper = new QueryWrapper<>();
//            orgQueryWrapper.eq("ywjgdm",form.getYwjg());
//            HrBasOrganization org = hrBasOrganizationService.getOne(orgQueryWrapper,false);
//            if (org == null) {
//                result.setSuccess(false);
//                result.setMsg("该业务机构在组织机构中不存在，请核实组织简称，并与其保持一致！");
//                return result;
//            }

            QueryWrapper<JylrhsZhbpftfy> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("fiscal_date", form.getFiscalDate());
            queryWrapper.eq("ywjg", form.getYwjg());
            JylrhsZhbpftfy record = service.getOne(queryWrapper,false);
            if (record != null) {
                result.setSuccess(false);
                result.setMsg("仅限新增导入，已有数据需手工删除后，才能再次导入！");
                return result;
            }
        }
        return result;
    }
}
