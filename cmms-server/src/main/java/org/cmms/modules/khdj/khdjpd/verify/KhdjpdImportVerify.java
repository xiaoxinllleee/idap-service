package org.cmms.modules.khdj.khdjpd.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.khdj.khdjpd.entity.Khdjpd;
import org.cmms.modules.khdj.khdjpd.service.IKhdjpdService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KhdjpdImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IKhdjpdService iKhdjpdService;
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
        Khdjpd khdjpd = (Khdjpd)var1;
        if (StringUtils.isEmpty(khdjpd.getPdzq())) {
            result.setSuccess(false);
            result.setMsg("评定周期不能为空");
            return result;
        }
        if (khdjpd.getPdrq() == null) {
            result.setSuccess(false);
            result.setMsg("评定日期不能为空");
            return result;
        }
        if (var3 == null) {
            return result;
        }
        //
        QueryWrapper<Khdjpd> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pdzq", khdjpd.getPdzq());
        queryWrapper.eq("pdrq", khdjpd.getPdrq());
        queryWrapper.eq("zjhm", var3);
        List<Khdjpd> list = iKhdjpdService.list(queryWrapper);
        if (!list.isEmpty()) {
            result.setSuccess(false);
            result.setMsg("已经存在的信息！");
            return result;
        }
        return result;
    }
}
