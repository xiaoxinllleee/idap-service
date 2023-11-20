package org.cmms.modules.jylrhs.jylrsj.fyftjz.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.DateUtils;
import org.cmms.modules.jylrhs.csgl.jgkmsz.entity.JylrhsKmszJg;
import org.cmms.modules.jylrhs.csgl.jgkmsz.service.IJylrhsKmszJgService;
import org.cmms.modules.jylrhs.csgl.jgzfyxz.entity.JylrhsZfyxzJg;
import org.cmms.modules.jylrhs.csgl.jgzfyxz.service.IJylrhsZfyxzJgService;
import org.cmms.modules.jylrhs.csgl.kmsz.entity.JylrhsKmsz;
import org.cmms.modules.jylrhs.csgl.kmsz.service.IJylrhsKmszService;
import org.cmms.modules.jylrhs.jylrsj.fyftjz.entity.JgfysxJehj;
import org.cmms.modules.jylrhs.jylrsj.fyftjz.entity.JylrhsFyftjz;
import org.cmms.modules.jylrhs.jylrsj.fyftjz.entity.JylrhsFyftjzVO;
import org.cmms.modules.jylrhs.jylrsj.fyftjz.service.IJgfysxJehjService;
import org.cmms.modules.jylrhs.jylrsj.fyftjz.service.IJylrhsFyftjzService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysRole;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysRoleService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.Bidi;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 经营利润核算（费用分摊记账）导入验证类
 *
 * @author penghr
 * @date 2023年6月8日
 */
@Slf4j
@Component
public class FyftjzImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IJylrhsFyftjzService jylrhsFyftjzService;
    @Autowired
    private IJylrhsKmszService kmszService;
    @Autowired
    private IJylrhsKmszJgService kmszJgService;
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
        JylrhsFyftjzVO form = (JylrhsFyftjzVO) var1;
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
            kmszQueryWrapper.eq("subject_no2",form.getJzkm());
            JylrhsKmsz kmsz = kmszService.getOne(kmszQueryWrapper,false);
            if (kmsz == null) {
                result.setSuccess(false);
                result.setMsg("该记账科目在科目设置内不存在，请核实！");
                return result;
            }
            kmszQueryWrapper = new QueryWrapper<>();
            kmszQueryWrapper.eq("szfl",form.getJzfl());
            kmszQueryWrapper.eq("subject_no2",form.getJzkm());
            kmsz = kmszService.getOne(kmszQueryWrapper,false);
            if (kmsz == null) {
                result.setSuccess(false);
                result.setMsg("记账分类下未含有该记账科目，请核实！");
                return result;
            }

            // 导入业务机构没有记账科目导入权限
//            QueryWrapper<JylrhsKmszJg> kmszJgQueryWrapper = new QueryWrapper<>();
//            kmszJgQueryWrapper.eq("jgdm",form.getJgdm());
//            kmszJgQueryWrapper.eq("subject_no2",form.getJzkm());
//            JylrhsKmszJg kmszJg = kmszJgService.getOne(kmszJgQueryWrapper,false);
//            if (kmszJg == null) {
//                result.setSuccess(false);
//                result.setMsg("当前机构没有当前记账科目的录入权限！");
//                return result;
//            }

            QueryWrapper<JylrhsFyftjz> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("fiscal_date", form.getFiscalDate());
            queryWrapper.eq("jgdm", form.getJgdm());
            queryWrapper.eq("jzfl", form.getJzfl());
            queryWrapper.eq("jzkm", form.getJzkm());
            JylrhsFyftjz record = jylrhsFyftjzService.getOne(queryWrapper,false);
            if (record != null) {
                // result.setSuccess(false);
                // result.setMsg("仅限新增导入，已有数据需手工删除后，才能再次导入！");
                // return result;
                jylrhsFyftjzService.remove(queryWrapper);
            }

            //当前机构、科目号上限额度
//            BigDecimal currentJgKmEd = BigDecimal.ZERO;
//            kmszJgQueryWrapper = new QueryWrapper<>();
//            kmszJgQueryWrapper.eq("jgdm",form.getJgdm());
//            kmszJgQueryWrapper.eq("subject_no2",form.getJzkm());
//            kmszJg = kmszJgService.getOne(kmszJgQueryWrapper,false);
//            if (kmszJg != null) {
//                currentJgKmEd = kmszJg.getSxed().setScale(2,BigDecimal.ROUND_HALF_UP);
//                if (form.getJe().compareTo(currentJgKmEd) > 0) {
//                    result.setSuccess(false);
//                    result.setMsg("当前机构分摊记账金额已超出机构科目设置上限金额"+currentJgKmEd.toString()+"，请核实！");
//                    return result;
//                }
//            }

        }
        return result;
    }
}
