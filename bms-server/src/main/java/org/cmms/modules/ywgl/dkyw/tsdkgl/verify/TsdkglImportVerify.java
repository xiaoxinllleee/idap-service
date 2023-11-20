package org.cmms.modules.ywgl.dkyw.tsdkgl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.khgl.dkkh.entity.CbsBormBase;
import org.cmms.modules.khgl.dkkh.service.ICbsBormBaseService;
import org.cmms.modules.ywgl.dkyw.tsdkgl.entity.TsdkglT;
import org.cmms.modules.ywgl.dkyw.tsdkgl.service.ITsdkglTService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TsdkglImportVerify implements IExcelVerifyHandler {
    @Autowired
    private ICbsBormBaseService cbsBormBaseService;

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
        TsdkglT table = (TsdkglT) obj;
        QueryWrapper<CbsBormBase> cbsBormBaseQueryWrapper = new QueryWrapper<>();
        cbsBormBaseQueryWrapper.eq("acct_no", table.getDkzh());
        CbsBormBase cbsBormBase = cbsBormBaseService.getOne(cbsBormBaseQueryWrapper);
        if (cbsBormBase == null) {
            result.setSuccess(false);
            result.setMsg("未找到贷款账号信息，请确认账号是否正确！");
            return result;
        }
        return result;
    }
}
