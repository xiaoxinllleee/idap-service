package org.cmms.modules.ywgl.nxt.shpj.glzhxx.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.cmms.modules.sjxf.hxxt.ckzdkb.entity.Ckzdkb;
import org.cmms.modules.sjxf.hxxt.ckzdkb.service.ICkzdkbService;
import org.cmms.modules.sjxf.hxxt.jjk.kzhglgx.entity.Kzhglgx;
import org.cmms.modules.sjxf.hxxt.jjk.kzhglgx.service.IKzhglgxService;
import org.cmms.modules.sjxf.hxxt.xjztdzb.entity.Xjztdzb;
import org.cmms.modules.sjxf.hxxt.xjztdzb.service.IXjztdzbService;
import org.cmms.modules.ywgl.nxt.shpj.glzhxx.entity.Glzhxx;
import org.cmms.modules.ywgl.nxt.shpj.glzhxx.service.IGlzhxxService;
import org.cmms.modules.ywgl.nxt.shpj.shjbxx.entity.Shjbxx;
import org.cmms.modules.ywgl.nxt.shpj.shjbxx.service.IShjbxxService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ShZhglxxImportVerify implements IExcelVerifyHandler {

    @Autowired
    private IHrBasStaffService hrBasStaffService;
    @Autowired
    private IGlzhxxService glzhxxService;
    @Autowired
    private IKzhglgxService kzhglgxService;
    @Autowired
    private IXjztdzbService xjztdzbService;
    @Autowired
    private IShjbxxService shjbxxService;

    @Autowired
    private ICkzdkbService ckzdkbService;

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
        Glzhxx table1 =(Glzhxx)var1;
        String ckzhStr= (String) var3;
        table1.setCkzh(ckzhStr);
        String zhmc ="";
        //检查是否存在于商户基本信息
        QueryWrapper queryWrapper =new QueryWrapper();
        queryWrapper.eq("shbm",table1.getShbm());
        Shjbxx check2 = shjbxxService.getOne(queryWrapper);

        if (check2==null) {
            result.setSuccess(false);
            result.setMsg("商户信息不存在于商户基本信息管理，请核对！");
            return result;
        }
        table1.setJgdm(check2.getJgdm());
        String dkzh = "";
        if (table1.getCkzh().length()==20){
            dkzh = "43"+table1.getCkzh();
            QueryWrapper queryWrapper1 =new QueryWrapper();
            queryWrapper1.eq("extn_ref_no",dkzh);
            Xjztdzb xjzhdzb = xjztdzbService.getOne(queryWrapper1);
            if (xjzhdzb==null){
                result.setSuccess(false);
                result.setMsg("存款帐号信息不存在！");
                return result;
            }
            QueryWrapper queryWrapper2 =new QueryWrapper();
            queryWrapper2.eq("acc_no",xjzhdzb.getIntnRefNo());
            Ckzdkb ckzh = ckzdkbService.getOne(queryWrapper2);
            if (ckzh!=null){
                if (ckzh.getAcctGrp().equals("P")&&ckzh.getAcctDesc().equals("S")){
                    table1.setZhlx(1);
                }else if (ckzh.getAcctGrp().equals("C")&&ckzh.getAcctDesc().equals("S")){
                    table1.setZhlx(2);
                }else{
                    result.setSuccess(false);
                    result.setMsg("存款账号/卡号[" + table1.getCkzh() + "]必须是对公活期或对私活期账号/卡号！");
                    return result;
                }
                zhmc = ckzh.getCustName();
            }else{
                result.setSuccess(false);
                result.setMsg("存款帐号信息不存在！");
                return result;
            }
            table1.setNewCkzh(ckzh.getSubAcctNo());
        }else if(table1.getCkzh().length()==17){
            QueryWrapper queryWrapper2 =new QueryWrapper();
            queryWrapper2.eq("sub_acct_no",table1.getCkzh());
            Ckzdkb ckzh = ckzdkbService.getOne(queryWrapper2);
            if (ckzh!=null){
                if (ckzh.getAcctGrp().equals("P")&&ckzh.getAcctDesc().equals("S")){
                    table1.setZhlx(1);
                }else if (ckzh.getAcctGrp().equals("C")&&ckzh.getAcctDesc().equals("S")){
                    table1.setZhlx(2);
                }else{
                    result.setSuccess(false);
                    result.setMsg("存款账号/卡号[" + table1.getCkzh() + "]必须是对公活期或对私活期账号/卡号！");
                    return result;

                }
                zhmc = ckzh.getCustName();
            }else{
                result.setSuccess(false);
                result.setMsg("存款帐号信息不存在！");
                return result;
            }
            table1.setNewCkzh(ckzh.getSubAcctNo());
        }else if(table1.getCkzh().length()==19){
            dkzh="0"+table1.getCkzh();

            QueryWrapper queryWrapper1 =new QueryWrapper();
            queryWrapper1.eq("card",dkzh);
            queryWrapper1.eq("iso_type","1");
            queryWrapper1.eq("is_primary","Y");
            Kzhglgx card = kzhglgxService.getOne(queryWrapper1);
            if (card==null){
                result.setSuccess(false);
                result.setMsg("存款帐号信息不存在！");
                return result;
            }


            QueryWrapper queryWrapper2 =new QueryWrapper();
            queryWrapper2.eq("sub_acct_no",card.getAccount());
            Ckzdkb ckzh = ckzdkbService.getOne(queryWrapper2);


            if (ckzh!=null){
                if (ckzh.getAcctGrp().equals("P")&&ckzh.getAcctDesc().equals("S")){
                    table1.setZhlx(1);
                }else if (ckzh.getAcctGrp().equals("C")&&ckzh.getAcctDesc().equals("S")){
                    table1.setZhlx(2);
                }else{
                    result.setSuccess(false);
                    result.setMsg("存款账号/卡号[" + table1.getCkzh() + "]必须是对公活期或对私活期账号/卡号！");
                    return result;
                }
                zhmc = ckzh.getCustName();
            }else{
                result.setSuccess(false);
                result.setMsg("存款帐号信息不存在！");
                return result;
            }
            table1.setNewCkzh(ckzh.getSubAcctNo());
        }
        if (StringUtils.isEmpty(zhmc)) {
            result.setSuccess(false);
            result.setMsg("核心系统户名为空！");
            return result;

        }
        if(!zhmc.equalsIgnoreCase(table1.getZhmc())){

            result.setSuccess(false);
            result.setMsg("账号名称[" + table1.getZhmc() + "]与核心系统的账号名称[" + zhmc + "]不一致，请核实！");
            return result;
        }

        QueryWrapper queryWrapper1= new QueryWrapper();
        queryWrapper1.eq("shbm",table1.getShbm());
        queryWrapper1.eq("ckzh",table1.getCkzh());

        Glzhxx check = glzhxxService.getOne(queryWrapper1);

        if (check!=null) {
            glzhxxService.remove(queryWrapper1);
        }
        table1.setGlrq(new Timestamp(System.currentTimeMillis()));
        table1.setLrr(sysUser.getUsername());
        table1.setLrrq(new Timestamp(System.currentTimeMillis()));
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
