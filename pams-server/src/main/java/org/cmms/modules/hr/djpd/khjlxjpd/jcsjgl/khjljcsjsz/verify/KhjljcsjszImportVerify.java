package org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.khjljcsjsz.verify;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.khjljcsjsz.entity.Khjljcsjsz;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.khjljcsjsz.service.IKhjljcsjszService;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zhdjgl.entity.Zhdjgl;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import org.cmms.modules.hr.yggl.ygrggl.service.IHrBasStaffPostService;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @date 2022/8/26 23:19 周五
 */
@Component
public class KhjljcsjszImportVerify implements IExcelVerifyHandler {

    @Autowired
    private IKhjljcsjszService khjljcsjszService;
    @Autowired
    private IHrBasStaffService hrBasStaffService;
    @Autowired
    private IHrBasStaffPostService hrBasStaffPostService;

    @Override
    public String[] getNeedVerifyFields() {
        return null;
    }

    @Override
    public void setNeedVerifyFields(String[] strings) {

    }

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object var1, String var2, Object var3) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        Khjljcsjsz table1 = (Khjljcsjsz) var1;
        Integer sfcypd = (Integer)var3;
        table1.setSfcypd(sfcypd);
        String pdzq = table1.getPdzq();
        String format = DateUtil.format(table1.getPdrq(), "yyyy-MM-dd");
        Date pdrq = DateUtil.parse(format);
        Date endDay = null;
        if ("YYYY".equalsIgnoreCase(pdzq)) {
            String substring = DateUtil.endOfYear(pdrq).toString().substring(0, 10);
            endDay = DateUtil.parse(substring);
        } else if ("MM".equalsIgnoreCase(pdzq)) {
            String substring = DateUtil.endOfMonth(pdrq).toString().substring(0, 10);
            endDay = DateUtil.parse(substring);
        } else if ("Q".equalsIgnoreCase(pdzq)) {
            String substring = DateUtil.endOfQuarter(pdrq).toString().substring(0, 10);
            endDay = DateUtil.parse(substring);
        }else {
            String substring = DateUtil.endOfDay(pdrq).toString().substring(0, 10);
            endDay = DateUtil.parse(substring);
        }
        QueryWrapper<HrBasStaff> staff = new QueryWrapper<>();
        staff.eq("ygxm",table1.getYgxm());
        if (StringUtils.isNotEmpty(table1.getKhjlbz())) {
            staff.eq("khjlbh",table1.getKhjlbz());
        }
        HrBasStaff staffOne = hrBasStaffService.getOne(staff, false);
        if (staffOne == null){
            result.setSuccess(false);
            result.setMsg("员工信息不存在！");
            return result;
        }
        table1.setYggh(staffOne.getYggh());


        QueryWrapper<HrBasStaffPost> staffPost = new QueryWrapper<HrBasStaffPost>();
        staffPost.eq("zzbz",table1.getZzbz());
        staffPost.eq("yggh",table1.getYggh());
        staffPost.eq("khjlbz",table1.getKhjlbz());
        staffPost.le("rgrq",endDay);
        staffPost.apply("  (lgrq is null or lgrq >= {0} )", pdrq);

        HrBasStaffPost staffPostOne = hrBasStaffPostService.getOne(staffPost,false);
        if (staffPostOne == null){
            result.setSuccess(false);
            result.setMsg("员工入岗信息不存在！");
            return result;
        }
        table1.setGwbz(staffPostOne.getGwbz());
        table1.setKhjlbz(staffPostOne.getKhjlbz());

        //检查是否存在手工修改过的数据
        QueryWrapper<Khjljcsjsz> check = new QueryWrapper<Khjljcsjsz>();
        check.eq("pdzq",table1.getPdzq());
        check.eq("pdrq",table1.getPdrq());
        check.eq("zzbz",table1.getZzbz());
        check.eq("gwbz",table1.getGwbz());
        check.eq("yggh",table1.getYggh());
        Khjljcsjsz check1 = khjljcsjszService.getOne(check,false);
        if (check1 != null) {
            khjljcsjszService.remove(check);
        }
        table1.setLrbz(0);
        table1.setLrsj(new Date());
        table1.setLrr(sysUser.getUsername());
        return result;

    }
}
