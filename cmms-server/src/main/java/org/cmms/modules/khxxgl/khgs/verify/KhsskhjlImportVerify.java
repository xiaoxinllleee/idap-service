package org.cmms.modules.khxxgl.khgs.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.khxxgl.khgs.entity.khsskhjl;
import org.cmms.modules.khxxgl.khgs.service.IkhsskhjlService;
import org.cmms.modules.khxxgl.khjbzl.entity.Khjbzl;
import org.cmms.modules.khxxgl.khjbzl.service.IKhjbzlService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KhsskhjlImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IKhjbzlService khjbzlService;
    @Autowired
    private IkhsskhjlService khsskhjlService;
    @Override
    public String[] getNeedVerifyFields() {
        return null;
    }

    @Override
    public void setNeedVerifyFields(String[] var1) {

    }

    /**
     *
     * @param var1 实体对象，包含设置interHandler的字段之前的所有字段值
     * @param var2 设置interHandler的字段名称
     * @param var3 设置interHandler的字段值
     * @return
     */
    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object var1, String var2, Object var3) {
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        if (var3 == null) {
            return result;
        }
        String khjl = (String) var3;
        khsskhjl khsskhjl = (khsskhjl)var1;
        QueryWrapper<Khjbzl> queryWrapper = new QueryWrapper<Khjbzl>();
        queryWrapper.eq("khmc", khsskhjl.getKhmc());
        queryWrapper.eq("zjhm", khsskhjl.getZjhm());
        List<Khjbzl> list =khjbzlService.list(queryWrapper);
        if (list.isEmpty()) {
            result.setSuccess(false);
            result.setMsg("未找到对应的客户信息！");
            return result;
        }
        QueryWrapper<khsskhjl> exists = new QueryWrapper<>();
        exists.eq("khmc", khsskhjl.getKhmc());
        exists.eq("zjhm", khsskhjl.getZjhm());
        exists.eq("sskhjl", khjl);
        List<khsskhjl> existsList = khsskhjlService.list(exists);
        if (!existsList.isEmpty()) {
            khsskhjlService.remove(exists);
        }
        khsskhjl.setDabh(list.get(0).getDabh());
        return result;
    }
}
