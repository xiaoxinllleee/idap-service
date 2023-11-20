package org.cmms.modules.khlc.tsckgl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.checkerframework.checker.units.qual.A;
import org.cmms.common.util.DateUtil;
import org.cmms.modules.khlc.tsckgl.entity.CbsInvmBase;
import org.cmms.modules.khlc.tsckgl.entity.Tsckgl;
import org.cmms.modules.khlc.tsckgl.service.ICbsInvmBaseService;
import org.cmms.modules.khlc.tsckgl.service.ITsckglService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TsckglImportVerify implements IExcelVerifyHandler {
    @Autowired
    private ICbsInvmBaseService cbsInvmBaseService;
    @Autowired
    private ITsckglService tsckglService;

    @Override
    public String[] getNeedVerifyFields() {
        return new String[0];
    }

    @Override
    public void setNeedVerifyFields(String[] arr) {

    }

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object obj, String name, Object value) {
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        Tsckgl table = (Tsckgl) obj;
        QueryWrapper<CbsInvmBase> cbsInvmBaseQueryWrapper = new QueryWrapper<>();
        cbsInvmBaseQueryWrapper.eq("sub_acct_no", table.getCkzh());
        CbsInvmBase cbsInvmBase = cbsInvmBaseService.getOne(cbsInvmBaseQueryWrapper);
        if (cbsInvmBase == null) {
            result.setSuccess(false);
            result.setMsg("未找到存款账号信息，请确认账号是否正确！");
            return result;
        }
        QueryWrapper<Tsckgl> existQueryWrapper = new QueryWrapper<>();
        existQueryWrapper.eq("ckzh", table.getCkzh());
        Tsckgl tsckgl = tsckglService.getOne(existQueryWrapper);
        if (tsckgl != null) {
            UpdateWrapper<Tsckgl> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("ckzh",table.getCkzh());
            tsckglService.remove(updateWrapper);
        }
        table.setJgdm(cbsInvmBase.getBranchNo());
        table.setCustName(cbsInvmBase.getCustName());
        table.setZjhm(cbsInvmBase.getIdentNo());
        table.setKhrq(DateUtil.string2Date(cbsInvmBase.getAcctOpenDt(), "yyyyMMdd"));
        table.setSubjNo(cbsInvmBase.getSubjNo());
        table.setSubjName(cbsInvmBase.getSubjName());
        return result;
    }
}
