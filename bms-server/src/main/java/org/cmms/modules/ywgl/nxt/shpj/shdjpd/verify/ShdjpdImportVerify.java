package org.cmms.modules.ywgl.nxt.shpj.shdjpd.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.cmms.modules.ywgl.nxt.cssz.entity.Shpjcssz;
import org.cmms.modules.ywgl.nxt.cssz.service.IShpjcsszService;
import org.cmms.modules.ywgl.nxt.shpj.shdjpd.entity.Shdjpd;
import org.cmms.modules.ywgl.nxt.shpj.shdjpd.service.IShdjpdService;
import org.cmms.modules.ywgl.nxt.shpj.shjbxx.entity.Shjbxx;
import org.cmms.modules.ywgl.nxt.shpj.shjbxx.service.IShjbxxService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class ShdjpdImportVerify implements IExcelVerifyHandler {

    @Autowired
    private IHrBasStaffService hrBasStaffService;

    @Autowired
    private IShdjpdService shdjpdService;
    @Autowired
    private IShjbxxService shjbxxService;

    @Autowired
    private IShpjcsszService shpjcsszService;

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
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        Shdjpd table1 =(Shdjpd)var1;
        Integer djsfytz = (Integer) var3;
        table1.setDjsfytz(djsfytz);
         //检查是否存在于商户基本信息
        QueryWrapper queryWrapper =new QueryWrapper();
        queryWrapper.eq("shbm",table1.getShbm());

        Shjbxx check2 = shjbxxService.getOne(queryWrapper);

        if (check2==null) {
            result.setSuccess(false);
            result.setMsg("商户信息不存在于商户基本信息管理，请核对！！");
            return result;
        }
        QueryWrapper queryWrapper1 =new QueryWrapper();
        queryWrapper1.eq("shbm",table1.getShbm());
        queryWrapper1.eq("pdzq",table1.getPdzq());
        queryWrapper1.eq("jgdm",table1.getJgdm());
        queryWrapper1.eq("pdlx",table1.getPdlx());

        Shdjpd check = shdjpdService.getOne(queryWrapper1);
        if(check!=null){
            if(check.getLrbz()!=null&&2==check.getLrbz()){
                result.setSuccess(false);
                result.setMsg("已存在手工修改过的数据！");
                return result;
            }
            shdjpdService.remove(queryWrapper1);
        }

        //获取评定等级
        String sqpddj = table1.getSqpddj();
        String bqpddj = table1.getBqpddj();
        table1.setSqpddj(null);
        table1.setBqpddj(null);

        List<Shpjcssz> list = shpjcsszService.list();
        if(list!=null&&list.size()>0){
            for (Shpjcssz pos_cssz :list){
                String csmc = pos_cssz.getCsmc();
                if(!StringUtils.isEmpty(csmc)){
                    table1.setSqpddj(pos_cssz.getCsbh());
                }
                if (csmc.equals(bqpddj)) {
                    table1.setBqpddj(pos_cssz.getCsbh());
                }
            }
        }

        table1.setShmc(check2.getShmc());
        table1.setJgdm(check2.getJgdm());
        table1.setKhjlbz(check2.getKhjlbz());
        table1.setLxdh(check2.getLxdh());
        table1.setPdrq(new Timestamp(System.currentTimeMillis()));
        table1.setLrrq(new Timestamp(System.currentTimeMillis()));
        table1.setLrr(sysUser.getUsername());
        table1.setLrbz(0);
        return result;
    }

    /**
     * 推广人员编号格式化
     * @param tgh
     * @return
     */
    public static String retTgh(String tgh){
        if(tgh!=null&&!"".equals(tgh)){
            tgh = tgh.replace(".","") ;
            return !tgh.equals("")&&tgh.length()>1?tgh.substring(0,tgh.length()).trim():tgh.trim();
        }
        return  "" ;
    }
}
