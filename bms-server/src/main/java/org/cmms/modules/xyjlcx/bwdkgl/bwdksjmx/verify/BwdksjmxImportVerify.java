package org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.entity.Bwdksjmx;
import org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.service.IBwdksjmxService;
import org.cmms.modules.xyjlcx.jcsjgl.csgl.entity.Csgl;
import org.cmms.modules.xyjlcx.jcsjgl.csgl.service.ICsglService;
import org.cmms.modules.xyjlcx.jcsjgl.djkdksjmx.entity.Djkdksjmx;
import org.cmms.modules.xyjlcx.jcsjgl.djkdksjmx.service.IDjkdksjmxService;
import org.cmms.modules.xyjlcx.sszxgl.ssgl.entity.Ssgl;
import org.cmms.modules.xyjlcx.sszxgl.ssgl.service.ISsglService;
import org.cmms.modules.ywgl.djkyw.djkrygl.entity.Djkrygl;
import org.cmms.modules.ywgl.djkyw.djkrygl.service.IDjkryglService;
import org.cmms.modules.ywgl.djkyw.djkwdgl.entity.Djkwdgl;
import org.cmms.modules.ywgl.djkyw.djkwdgl.service.IDjkwdglService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;

@Component
public class BwdksjmxImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IBwdksjmxService bwdksjmxService;
    @Autowired
    private ISsglService ssglService;
    @Autowired
    private ICsglService csglService;




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
        Bwdksjmx bwdksjmx = (Bwdksjmx) var1;
        String dkzh = (String)var3;

        QueryWrapper<Bwdksjmx> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dkzh",dkzh);
        Bwdksjmx bwdksjmx1 = bwdksjmxService.getOne(queryWrapper);
        if (bwdksjmx1 != null){
            if (!StringUtils.isEmpty(bwdksjmx.getLxfs())){
                DecimalFormat df = new DecimalFormat("#0");
                try {
                    bwdksjmx1.setLxfs(df.format(Double.parseDouble(bwdksjmx.getLxfs())));
                }catch (Throwable tx){
                    bwdksjmx1.setLxfs(bwdksjmx.getLxfs());
                }
            }
            if (!StringUtils.isEmpty(bwdksjmx.getKhdz())){
                bwdksjmx1.setKhdz(bwdksjmx.getKhdz());
            }
            if (!StringUtils.isEmpty(bwdksjmx.getKhzk())){
                bwdksjmx1.setKhzk(bwdksjmx.getKhzk());
            }
            if (bwdksjmx.getDqrq() != null){
                bwdksjmx1.setDqrq(bwdksjmx.getDqrq());
            }
            if (bwdksjmx.getDkrq() != null){
                bwdksjmx1.setDkrq(bwdksjmx.getDkrq());
            }
            if (bwdksjmx.getJkje() != null){
                if (!StringUtils.isEmpty(String.valueOf(bwdksjmx.getJkje()))){
                    bwdksjmx1.setJkje(bwdksjmx.getJkje());
                }
            }
            if (!StringUtils.isEmpty(bwdksjmx.getDktx())){
                bwdksjmx1.setDktx(bwdksjmx.getDktx());
            }
            if (!StringUtils.isEmpty(bwdksjmx.getBszrr())){
                bwdksjmx1.setBszrr(bwdksjmx.getBszrr());
            }
            if (!StringUtils.isEmpty(bwdksjmx.getGlzrr())){
                bwdksjmx1.setGlzrr(bwdksjmx.getGlzrr());
            }
            if (bwdksjmx.getZjcsrq() != null){
                bwdksjmx1.setZjcsrq(bwdksjmx.getZjcsrq());
            }

            boolean sssxUpdate = false;
            QueryWrapper<Ssgl> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("zh",dkzh);
            Ssgl ssgl = ssglService.getOne(queryWrapper1);
            if (ssgl != null){
                bwdksjmx1.setSssx("1");
                sssxUpdate = true;
            }else {
                if (bwdksjmx.getDqrq() != null){
                    long time = DateUtil.getFrontYearTime(bwdksjmx.getDqrq().getTime(), -2);
                    if (time > System.currentTimeMillis()){
                        bwdksjmx1.setSssx("1");
                        sssxUpdate = true;
                    }else {
                        bwdksjmx1.setSssx("0");
                    }
                }
            }

            if (bwdksjmx.getZjcsrq() != null){
                //计算诉讼时效与诉讼时效到期日
                QueryWrapper<Csgl> queryWrapper2 = new QueryWrapper<>();
                queryWrapper2.eq("csbm","P00008");
                Csgl csgl = csglService.getOne(queryWrapper2);
                String sssxzq = "2";
                if (csgl != null){
                    sssxzq = csgl.getCsz();
                }
                long time = DateUtil.getFrontYearTime(bwdksjmx.getZjcsrq().getTime(), -Integer.parseInt(sssxzq));
                if (time > System.currentTimeMillis()){
                    bwdksjmx1.setSssx("1");
                }else {
                    if (!sssxUpdate){
                        bwdksjmx1.setSssx("0");
                    }
                }
                bwdksjmx1.setSssxdqr(new Timestamp(time));
            }else {
                if (!sssxUpdate){
                    bwdksjmx1.setSssx("0"); //默认无效
                }
                bwdksjmx1.setSssxdqr(null);
            }

            if (bwdksjmx.getZjlx() != null){
                bwdksjmx1.setZjlx(bwdksjmx.getZjlx());
            }
            if (bwdksjmx.getZjhm() != null){
                bwdksjmx1.setZjhm(bwdksjmx.getZjhm());
            }
            if (!StringUtils.isEmpty(bwdksjmx.getQksm())){
                bwdksjmx1.setQksm(bwdksjmx.getQksm());
            }
            bwdksjmx1.setLrbz(2);
            bwdksjmx1.setLrsj(new Date());
            bwdksjmx1.setLrr(sysUser.getRealname());

            bwdksjmxService.update(bwdksjmx1,queryWrapper);

            result.setSuccess(false);
            result.setMsg("账号信息修改成功！");
            return result;
        }else {
            result.setSuccess(false);
            result.setMsg("贷款账号不存在，请核实！");
            return result;
        }
    }

}
