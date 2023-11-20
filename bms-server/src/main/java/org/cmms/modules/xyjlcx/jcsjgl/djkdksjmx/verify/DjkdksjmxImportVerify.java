package org.cmms.modules.xyjlcx.jcsjgl.djkdksjmx.verify;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.xyjlcx.jcsjgl.djkdksjmx.entity.Djkdksjmx;
import org.cmms.modules.xyjlcx.jcsjgl.djkdksjmx.service.IDjkdksjmxService;
import org.cmms.modules.ywgl.djkyw.djkrygl.entity.Djkrygl;
import org.cmms.modules.ywgl.djkyw.djkrygl.service.IDjkryglService;
import org.cmms.modules.ywgl.djkyw.djkwdgl.entity.Djkwdgl;
import org.cmms.modules.ywgl.djkyw.djkwdgl.service.IDjkwdglService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
public class DjkdksjmxImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IDjkdksjmxService djkdksjmxService;
    @Autowired
    private IDjkwdglService djkwdglService;
    @Autowired
    private IDjkryglService djkryglService;
    @Autowired
	private IHrBasOrganizationService hrBasOrganizationService;




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
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        Djkdksjmx djkdksjmx = (Djkdksjmx) var1;
        String kztbz = (String) var3;
        djkdksjmx.setKztbz(kztbz);
        String strTgh = djkdksjmx.getTgrgh().replaceAll(" ", "");//根据推广人员编号截取机构代码和员工号
        String strTgh2 = retTgh(strTgh);
        if (strTgh2.length()<13){
            result.setSuccess(false);
            result.setMsg("推广人员编号格式有误！");
            return result;
        }
        String strJgdm = strTgh2.substring(0, 8);
        String strJob = strTgh2.substring(8, 13);

        QueryWrapper<Djkwdgl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tgjgbh",strJgdm);
        Djkwdgl djkwd = djkwdglService.getOne(queryWrapper,false);
        if (djkwd == null){
            result.setSuccess(false);
            result.setMsg("请先在贷记卡网点关联中维护推广人员编码的机构代码!");
            return result;
        }else {
            djkdksjmx.setYwjg(djkwd.getJgdm());
        }

        QueryWrapper<Djkrygl> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("tgrybh",strJob);
        Djkrygl djkrygl = djkryglService.getOne(queryWrapper1,false);
        if (djkrygl == null){
            result.setSuccess(false);
            result.setMsg(" 请先在贷记卡人员关联中维护推广人员编码对应的员工号！");
            return result;
        }else {
            djkdksjmx.setYggh(djkrygl.getYggh());
        }

        QueryWrapper<HrBasOrganization> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("ywjgdm",djkdksjmx.getYwjg());
        HrBasOrganization hrBasOrganization = hrBasOrganizationService.getOne(queryWrapper2,false);
        if (hrBasOrganization == null){
            result.setSuccess(false);
            result.setMsg("该业务机构信息不存在");
            return result;
        }

        long time = DateUtil.getFrontYearTime(djkdksjmx.getFkrq().getTime(), -3);
        Date date = new Date(time);
        date = DateUtil.getMonthEndDay(date);
        djkdksjmx.setDqrq(new Timestamp(date.getTime()));
        djkdksjmx.setLrbz(0);
        djkdksjmx.setLrsj(new Timestamp(System.currentTimeMillis()));
        djkdksjmx.setLrr(sysUser.getRealname());
        return result;
    }

    /**
     * 推广人编号格式化
     */
    public static String retTgh(String tgh){
        if (tgh != null && !"".equals(tgh)){
            tgh = tgh.replace(".","");
            return !tgh.equals("")&&tgh.length()>1?tgh.substring(0,tgh.length()).trim():tgh.trim();
        }
        return "";
    }

}
