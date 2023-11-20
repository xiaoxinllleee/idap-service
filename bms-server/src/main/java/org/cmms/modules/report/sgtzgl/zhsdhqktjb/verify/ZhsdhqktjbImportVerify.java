package org.cmms.modules.report.sgtzgl.zhsdhqktjb.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.report.sgtzgl.gmgyqymd.entity.SgztGmgyqymd;
import org.cmms.modules.report.sgtzgl.zhsdhqktjb.entity.SgztZhsdhqktjb;
import org.cmms.modules.report.sgtzgl.zhsdhqktjb.service.impl.SgztZhsdhqktjbServiceImpl;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 龚辉
 * @date 2022/8/27 0:32 周六
 */
@Component
public class ZhsdhqktjbImportVerify implements IExcelVerifyHandler {

    @Autowired
    private SgztZhsdhqktjbServiceImpl sgztZhsdhqktjbService;

    @Override
    public String[] getNeedVerifyFields() {
        return null;
    }

    @Override
    public void setNeedVerifyFields(String[] strings) {

    }


    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object var1, String var2, Object var3) {
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        System.out.println("var1" + var1);
        System.out.println("var3" + var3);
        SgztZhsdhqktjb form = (SgztZhsdhqktjb) var1;
        QueryWrapper<SgztZhsdhqktjb> queryWrapper = new QueryWrapper<>();
        SgztZhsdhqktjb qkmb1 = sgztZhsdhqktjbService.getOne(queryWrapper);
        sgztZhsdhqktjbService.update(qkmb1, queryWrapper);
        result.setSuccess(false);
        result.setMsg("导入成功！");
        return result;
    }
}
