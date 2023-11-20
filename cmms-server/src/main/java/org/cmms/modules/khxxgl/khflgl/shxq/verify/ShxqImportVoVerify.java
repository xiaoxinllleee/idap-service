package org.cmms.modules.khxxgl.khflgl.shxq.verify;

import cn.hutool.core.util.IdcardUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.khxxgl.khflgl.shxq.entity.ShxqImportVo;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMain;
import org.cmms.modules.yxdygl.yxdyglmain.service.IYxdyglMainService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * @Date 2022/4/22
 * @Created by eran
 */
@Component
public class ShxqImportVoVerify implements IExcelVerifyHandler {

    @Autowired
    IYxdyglMainService yxdyglMainService;
    @Autowired
    private IHrBasOrganizationService hrBasOrganizationService;
    @Override
    public String[] getNeedVerifyFields() {
        return new String[0];
    }

    @Override
    public void setNeedVerifyFields(String[] arr) {

    }

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object var1, String var2, Object var3) {
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        ShxqImportVo table1 =(ShxqImportVo)var1;
        QueryWrapper queryWrapper = new QueryWrapper();
        Object wgbh = queryWrapper.eq("wgbh", table1.getWgbh());
        if (wgbh == null){
            result.setSuccess(false);
            result.setMsg("归属网格编号不存在！");
            return result;
        }

      /*  QueryWrapper queryWrapperZzbz =new QueryWrapper();
        queryWrapperZzbz.eq("ywjgdm",table1.getJgdm());
        HrBasOrganization hrBasOrganization = hrBasOrganizationService.getOne(queryWrapperZzbz);
        if (hrBasOrganization == null){
            result.setSuccess(false);
            result.setMsg("归属机构不存在！");
            return result;
        }*/
        return result;
    }
}
