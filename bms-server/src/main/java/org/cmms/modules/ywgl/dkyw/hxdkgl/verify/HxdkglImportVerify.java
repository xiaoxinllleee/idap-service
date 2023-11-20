package org.cmms.modules.ywgl.dkyw.hxdkgl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.util.LangUtil;
import org.cmms.common.util.DateUtil;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.cmms.modules.sjxf.hxxt.dkzdkb.entity.Dkzdkb;
import org.cmms.modules.sjxf.hxxt.dkzdkb.service.IDkzdkbService;
import org.cmms.modules.sjxf.xdxt.dkywjjb.entity.Dkywjjb;
import org.cmms.modules.sjxf.xdxt.dkywjjb.service.IDkywjjbService;
import org.cmms.modules.ywgl.dkyw.hxdkgl.entity.Hxdkgl;
import org.cmms.modules.ywgl.dkyw.hxdkgl.entity.HxdkglVo;
import org.cmms.modules.ywgl.dkyw.hxdkgl.service.IHxdkglService;
import org.cmms.modules.ywgl.dkyw.lsdksjgl.entity.Lsdksjgl;
import org.cmms.modules.ywgl.dkyw.lsdksjgl.service.ILsdksjglService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
public class HxdkglImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IHxdkglService hxdkglService;

    @Autowired
    private IHrBasStaffService hrBasStaffService;

    @Autowired
    private IDkzdkbService dkzdkbService;

    @Autowired
    private IDkywjjbService dkywjjbService;

    @Autowired
    private ILsdksjglService lsdksjglService;

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
        Hxdkgl hxdkgl =(Hxdkgl)var1;
        String yggh= hxdkgl.getJobnumber();
        QueryWrapper queryWrapper= new QueryWrapper();
        queryWrapper.eq("yggh",yggh);
        HrBasStaff staff = hrBasStaffService.getOne(queryWrapper);
        if(staff==null){
            result.setSuccess(false);
            result.setMsg("员工信息不存在!");
            return result;
        }
        QueryWrapper  queryWrapperDkzb =new QueryWrapper();
        queryWrapperDkzb.eq("acct_no",hxdkgl.getAcctNo());
        Dkzdkb dkyeb = dkzdkbService.getOne(queryWrapperDkzb);
        if(dkyeb==null){
            QueryWrapper  queryWrapperLsdksj =new QueryWrapper();
            queryWrapperLsdksj.eq("acct_no",hxdkgl.getAcctNo());
            Lsdksjgl lsdksj = lsdksjglService.getOne(queryWrapperLsdksj);
            if (lsdksj==null) {
                result.setSuccess(false);
                result.setMsg("贷款账号不存在！");
                return result;
            }

            if (lsdksj.getBalance().doubleValue() > 0) {
                result.setSuccess(false);
                result.setMsg("此账号在贷款历史表中还有贷款余额【"+String.valueOf(dkyeb.getLoanBal().doubleValue())+"】，不允许添加为已核销贷款！");
                return result;
            }

            hxdkgl.setOrg(lsdksj.getOrg());
            hxdkgl.setFinInsName(lsdksj.getFinInsName());
            hxdkgl.setPutOutDate(lsdksj.getPutOutDate());
            hxdkgl.setMinCalcDate(lsdksj.getMinCalcDate());
            hxdkgl.setMaturity(lsdksj.getMaturity());
            hxdkgl.setPutoutSum(lsdksj.getPutoutSum());
            hxdkgl.setCtfcCd(lsdksj.getCtfcCd());
            hxdkgl.setTotleBalance(lsdksj.getTotleBalance());
            hxdkgl.setAppRate(lsdksj.getAppRate());
            hxdkgl.setAppSum(lsdksj.getAppSum());
            hxdkgl.setAppTerm(lsdksj.getAppTerm());
            hxdkgl.setCustId(lsdksj.getCustId());
            hxdkgl.setRate(lsdksj.getRate());
            hxdkgl.setYqll(lsdksj.getYqll());
            hxdkgl.setActualPurpose(lsdksj.getActualPurpose());
            hxdkgl.setBusinessNo(lsdksj.getBusinessNo());
            hxdkgl.setBusinessPhase(lsdksj.getBusinessPhase());
            hxdkgl.setCardNo(lsdksj.getCardNo());
            hxdkgl.setContractNo(lsdksj.getContractNo());
            hxdkgl.setCustBusadd(lsdksj.getCustBusadd());
            hxdkgl.setCustCn(lsdksj.getCustCn());
            hxdkgl.setCustCn1(lsdksj.getCustCn1());
            hxdkgl.setCustCn2(lsdksj.getCustCn2());
            hxdkgl.setCustName(lsdksj.getCustName());
            hxdkgl.setCustTel(lsdksj.getCustTel());
            hxdkgl.setJzkm(lsdksj.getJzkm());
            hxdkgl.setCurrency(lsdksj.getCurrency());
            hxdkgl.setVoucherNo(lsdksj.getVoucherNo());
            hxdkgl.setVouchType(lsdksj.getVouchType());
            hxdkgl.setQxDate(lsdksj.getQxDate());
            hxdkgl.setCustManagerId(lsdksj.getCustManagerId());
        }else{
            if (dkyeb.getLoanBal().doubleValue() > 0) {
                result.setSuccess(false);
                result.setMsg("此账号在贷款余额表中还有贷款余额【"+String.valueOf(dkyeb.getLoanBal().doubleValue())+"】，不允许添加为已核销贷款！");
                return result;
            }
            hxdkgl.setOrg(dkyeb.getBrNo());
            hxdkgl.setFinInsName(dkyeb.getBrName());
            hxdkgl.setPutOutDate(new Timestamp(DateUtil.string2Date(dkyeb.getQxDate(), "yyyyMMdd").getTime()));
            hxdkgl.setMaturity(new Timestamp(DateUtil.string2Date(dkyeb.getEndDate(), "yyyyMMdd").getTime()));
            hxdkgl.setPutoutSum(dkyeb.getAdvVal());
            hxdkgl.setCtfcCd(dkyeb.getIdentNo());
            hxdkgl.setAppRate(dkyeb.getIntRate());
            hxdkgl.setRate(dkyeb.getIntRate());
            hxdkgl.setBusinessNo(dkyeb.getBusinessNo());
            hxdkgl.setBusinessPhase(dkyeb.getBusinessPhase());
            hxdkgl.setCardNo(dkyeb.getCardNo());
            hxdkgl.setCustCn(dkyeb.getCustCn());
            hxdkgl.setCustName(dkyeb.getCustName());
            hxdkgl.setCustTel(dkyeb.getLinkmanTel());
            hxdkgl.setJzkm(dkyeb.getSubjNo());
            hxdkgl.setCurrency(dkyeb.getCurr());
            hxdkgl.setVoucherNo(dkyeb.getVoucherNo());
            hxdkgl.setVouchType(dkyeb.getVouchType());
            hxdkgl.setQxDate(new Timestamp(DateUtil.string2Date(dkyeb.getIntStrtDate(), "yyyyMMdd").getTime()));

            QueryWrapper  queryWrapperDkywjjb =new QueryWrapper();
            queryWrapperDkywjjb.eq("acct_no",hxdkgl.getAcctNo());
            Dkywjjb cms_business_due = dkywjjbService.getOne(queryWrapperDkywjjb);
            if(cms_business_due!=null){
                hxdkgl.setCustManagerId(cms_business_due.getUserId());
            }
        }
        if (StringUtils.isEmpty(hxdkgl.getCustManagerId())) {
            hxdkgl.setCustManagerId(staff.getKhjlbh());
        }

        return result;
    }
}
