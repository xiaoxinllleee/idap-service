package org.cmms.modules.ywgl.dkyw.dkzhzy.verify;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import org.cmms.modules.hr.yggl.ygrggl.service.IHrBasStaffPostService;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.cmms.modules.sjxf.hxxt.dkzdkb.entity.Dkzdkb;
import org.cmms.modules.sjxf.hxxt.dkzdkb.service.IDkzdkbService;
import org.cmms.modules.ywgl.ckyw.ckzhgl.entity.Ckzhgl;
import org.cmms.modules.ywgl.ckyw.ckzhgl.service.ICkzhglService;
import org.cmms.modules.ywgl.dkyw.dkzhzy.entity.Dkzhzy;
import org.cmms.modules.ywgl.dkyw.dkzhzy.entity.DkzhzyImportVo;
import org.cmms.modules.ywgl.dkyw.dkzhzy.service.IDkzhzyService;
import org.cmms.modules.ywgl.dkyw.lsdksjgl.entity.Lsdksjgl;
import org.cmms.modules.ywgl.dkyw.lsdksjgl.service.ILsdksjglService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Component
public class DkzhzyImportVerify implements IExcelVerifyHandler {

    @Autowired
    private IDkzhzyService dkzhzyService;
    @Autowired
    private IHrBasStaffService hrBasStaffService;

    @Autowired
    private IDkzdkbService dkzdkbService;


    @Autowired
    private ILsdksjglService lsdksjglService;

    @Autowired
    private ICkzhglService ckzhglService;

    @Autowired
    private IHrBasStaffPostService hrBasStaffPostService;


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

        DkzhzyImportVo table1 =(DkzhzyImportVo)var1;
        String yggh= table1.getJobnumber();
        String dkzh= (String) var3;
        QueryWrapper queryWrapper= new QueryWrapper();
        queryWrapper.eq("yggh",yggh);
        HrBasStaff staff = hrBasStaffService.getOne(queryWrapper);
        if(staff==null){
            result.setSuccess(false);
            result.setMsg("员工信息不存在!");
            return result;
        }


        QueryWrapper queryWrapperYggwxx= new QueryWrapper();
        queryWrapperYggwxx.eq("yggh",yggh);
        queryWrapperYggwxx.isNotNull("khjlbz");
        List<HrBasStaffPost> gwxx = hrBasStaffPostService.list(queryWrapperYggwxx);
        if(gwxx==null||gwxx.size()==0){
            result.setSuccess(false);
            result.setMsg("员工入岗信息中没有为此员工配置客户经理标识信息！!");
            return result;
        }


        QueryWrapper  queryWrapperDkzb =new QueryWrapper();
        queryWrapperDkzb.eq("acct_no",dkzh);
        Dkzdkb cbs_borm_base = dkzdkbService.getOne(queryWrapperDkzb);
        if(cbs_borm_base==null){
            QueryWrapper  queryWrapperLsdksj =new QueryWrapper();
            queryWrapperLsdksj.eq("acct_no",dkzh);
            Lsdksjgl lsdksj = lsdksjglService.getOne(queryWrapperLsdksj);
            if (lsdksj==null) {
                result.setSuccess(false);
                result.setMsg("贷款账号不存在！");
                return result;
            }
            //补充贷款余额表中的记录
            table1.setOrg(lsdksj.getOrg());
            table1.setPutOutDate(lsdksj.getPutOutDate());
            table1.setMinCalcDate(lsdksj.getMinCalcDate());
            table1.setMaturity(lsdksj.getMaturity());
            table1.setPutoutSum(lsdksj.getPutoutSum());
            table1.setCtfcCd(lsdksj.getCtfcCd());
            table1.setTotleBalance(lsdksj.getTotleBalance());
            table1.setAppRate(lsdksj.getAppRate());
            table1.setAppSum(lsdksj.getAppSum());
            table1.setAppTerm(lsdksj.getAppTerm());
            table1.setCustId(lsdksj.getCustId());
            table1.setRate(lsdksj.getRate());
            table1.setYqll(lsdksj.getYqll());
            table1.setActualPurpose(lsdksj.getActualPurpose());
            table1.setBusinessNo(lsdksj.getBusinessNo());
            table1.setBusinessPhase(lsdksj.getBusinessPhase());
            table1.setCardNo(lsdksj.getCardNo());
            table1.setContractNo(lsdksj.getContractNo());
            table1.setCustBusadd(lsdksj.getCustBusadd());
            table1.setCustCn(lsdksj.getCustCn());
            table1.setCustCn1(lsdksj.getCustCn1());
            table1.setCustCn2(lsdksj.getCustCn2());
            table1.setCustName(lsdksj.getCustName());
            table1.setCustTel(lsdksj.getCustTel());
            table1.setJzkm(lsdksj.getJzkm());
            table1.setCurrency(lsdksj.getCurrency());
            table1.setVoucherNo(lsdksj.getVoucherNo());
            table1.setVouchType(lsdksj.getVouchType());
            table1.setQxDate(lsdksj.getQxDate());
            table1.setBalance(lsdksj.getBalance());
        } else {
            table1.setOrg(cbs_borm_base.getBrNo());
            table1.setPutOutDate(new Timestamp(DateUtil.string2Date(cbs_borm_base.getQxDate(), "yyyyMMdd").getTime()));
            table1.setMaturity(new Timestamp(DateUtil.string2Date(cbs_borm_base.getEndDate(), "yyyyMMdd").getTime()));
            table1.setPutoutSum(cbs_borm_base.getApplicAmount());
            table1.setCtfcCd(cbs_borm_base.getIdentNo());
            table1.setRate(cbs_borm_base.getIntRate());
            table1.setAppSum(cbs_borm_base.getAppSum());
            table1.setAppTerm(cbs_borm_base.getAppTerm() == null ? null : Long.valueOf(cbs_borm_base.getAppTerm()));
            table1.setCustId(cbs_borm_base.getCustId() == null ? null : Long.valueOf(cbs_borm_base.getCustId()));
            table1.setBusinessNo(cbs_borm_base.getBusinessNo());
            table1.setBusinessPhase(cbs_borm_base.getBusinessPhase());
            table1.setCardNo(cbs_borm_base.getCardNo());
            table1.setCustBusadd(cbs_borm_base.getAddr());
            table1.setCustCn(cbs_borm_base.getCustCn());
            table1.setCustName(cbs_borm_base.getCustName());
            table1.setCustTel(cbs_borm_base.getLinkmanTel());
            table1.setJzkm(cbs_borm_base.getSubjNo());
            table1.setCurrency(cbs_borm_base.getCurr());
            table1.setVoucherNo(cbs_borm_base.getVoucherNo());
            table1.setVouchType(cbs_borm_base.getVouchType());
            if (StringUtils.isNotEmpty(cbs_borm_base.getIntStrtDate())) {
                table1.setQxDate(new Timestamp(DateUtil.string2Date(cbs_borm_base.getIntStrtDate(), "yyyyMMdd").getTime()));
            }
            table1.setBalance(cbs_borm_base.getLoanBal().setScale(2,BigDecimal.ROUND_HALF_UP));
        }
        table1.setCustManagerId(gwxx.get(0).getKhjlbz());

        QueryWrapper queryWrapper1=new QueryWrapper();
        queryWrapper1.eq("acct_no",dkzh);
        Dkzhzy dkzhzy = dkzhzyService.getOne(queryWrapper1);
        if(dkzhzy!=null){
            dkzhzyService.remove(queryWrapper1);
        }

        if(cbs_borm_base!=null){
            String jxzh = cbs_borm_base.getAutoDbtAcctNo();
            if (!StringUtils.isEmpty(jxzh)) {
                QueryWrapper queryWrapper2 =new QueryWrapper();
                queryWrapper2.eq("ckzh",jxzh);
                queryWrapper2.eq("yxbz",1);
                Ckzhgl ckzhglxx = ckzhglService.getOne(queryWrapper2);
                if(ckzhglxx!=null){
                    QueryWrapper queryWrapper3=new QueryWrapper();
                    queryWrapper3.eq("glid",ckzhglxx.getGlid());
                    ckzhglService.remove(queryWrapper3);
                    Ckzhgl insert=new Ckzhgl();
                    //insert.setGlid(nextSeq("seq_zhgl_glid"));
                    insert.setGlid(UUIDGenerator.uniqId());
                    insert.setZzbz(gwxx.get(0).getZzbz());
                    insert.setGwbz(gwxx.get(0).getGwbz());
                    insert.setYggh(gwxx.get(0).getYggh());
                    insert.setGyh(gwxx.get(0).getGyh());
                    insert.setKhjlbz(gwxx.get(0).getKhjlbz());
                    insert.setYwjgdm(ckzhglxx.getYwjgdm());
                    insert.setCkzh(jxzh);
                    insert.setZhxz(ckzhglxx.getZhxz());
                    insert.setGlbz(1);
                    insert.setGlbl(ckzhglxx.getGlbl());
                    insert.setYxbz(1);
                    insert.setLrczy(sysUser.getUsername());
                    insert.setLrsj(new Timestamp(System.currentTimeMillis()));
                    insert.setLrbz(1);
                    insert.setZhmc(ckzhglxx.getZhmc());
                    insert.setDkzh(ckzhglxx.getDkzh());
                    ckzhglService.save(insert);
                }
            }
        }


        return result;
    }
}
