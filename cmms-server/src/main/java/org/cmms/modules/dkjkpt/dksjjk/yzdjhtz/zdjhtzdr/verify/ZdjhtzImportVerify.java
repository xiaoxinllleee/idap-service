package org.cmms.modules.dkjkpt.dksjjk.yzdjhtz.zdjhtzdr.verify;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.dkjkpt.dksjjk.yzdjhtz.zdjhtzdr.entity.DkjkptZdjhtzdr;
import org.cmms.modules.dkjkpt.dksjjk.yzdjhtz.zdjhtzdr.service.IDkjkptZdjhtzdrService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author
 * @date 2022/8/26 23:19 周五
 */
@Component
public class ZdjhtzImportVerify implements IExcelVerifyHandler {

    @Autowired
    private IDkjkptZdjhtzdrService dkjkptZdjhtzdrService;

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
        DkjkptZdjhtzdr form = (DkjkptZdjhtzdr) var1;
        String bz = (String)var3;
        form.setBz(bz);

        QueryWrapper<DkjkptZdjhtzdr> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sjrq",form.getSjrq());
        queryWrapper.eq("jgdm",form.getJgdm());
        queryWrapper.eq("zjhm",form.getZjhm());
        queryWrapper.eq("qdlx",form.getQdlx());
        queryWrapper.eq("hkpl",form.getHkpl());
        DkjkptZdjhtzdr serviceOne = dkjkptZdjhtzdrService.getOne(queryWrapper);
        if (serviceOne != null){
            result.setSuccess(false);
            result.setMsg("该客户信息已存在！");
            return result;
        }

        return result;

    }
}
