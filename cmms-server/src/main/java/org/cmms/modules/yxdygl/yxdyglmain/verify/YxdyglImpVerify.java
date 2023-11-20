package org.cmms.modules.yxdygl.yxdyglmain.verify;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.khdj.khdjpd.entity.Khdjpd;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMain;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMainExp;
import org.cmms.modules.yxdygl.yxdyglmain.service.IYxdyglMainService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Date 2021/12/14
 * @Created by eran
 */
@Component
public class YxdyglImpVerify implements IExcelVerifyHandler {
    @Autowired
    IYxdyglMainService yxdyglMainService;
    @Autowired
    IHrBasOrganizationService hrBasOrganizationService;

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
        YxdyglMainExp yxdyglMainExp = (YxdyglMainExp) obj;

        if (StringUtils.isEmpty(yxdyglMainExp.getWgmc())) {
            result.setSuccess(false);
            result.setMsg("网格名称不能为空");
            return result;
        }

        if (StringUtils.isEmpty(yxdyglMainExp.getWgmc())){
            result.setSuccess(false);
            result.setMsg("网格编号不能为空");
            return result;
        }else {
            LambdaQueryWrapper<YxdyglMain> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(YxdyglMain::getWgbh,yxdyglMainExp.getWgbh().trim());
            List<YxdyglMain> list = yxdyglMainService.list(lambdaQueryWrapper);
            if (CollUtil.isNotEmpty(list)){
                result.setSuccess(false);
                result.setMsg("网格编号已经存在");
                return result;
            }

        }


        if (StringUtils.isEmpty(yxdyglMainExp.getParentId())) {
            result.setSuccess(false);
            result.setMsg("上级网格名称不能为空");
            return result;
        }else {
            LambdaQueryWrapper<YxdyglMain> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(YxdyglMain::getWgmc,yxdyglMainExp.getWgmc().trim());
            Long count = yxdyglMainService.count(lambdaQueryWrapper);
            if (count == 0){
                result.setSuccess(false);
                result.setMsg("该上级网格名称不存在");
                return result;
            }
        }

        if (StringUtils.isEmpty(yxdyglMainExp.getWgxz())) {
            result.setSuccess(false);
            result.setMsg("网格性质不能为空");
            return result;
        }else {
            String wgxz = yxdyglMainExp.getWgxz().trim();
            if ("镇".equals(wgxz) || "村".equals(wgxz) || "组".equals(wgxz) || "城区街道".equals(wgxz) || "社区".equals(wgxz) ||"商圈".equals(wgxz)){
                result.setSuccess(false);
                result.setMsg("网格性质不存在,镇,村,组,城区街道,社区,商圈等中选择！");
                return result;
            }
        }

        if (StringUtils.isEmpty(yxdyglMainExp.getZzbz())) {
            result.setSuccess(false);
            result.setMsg("所属支行不能为空");
            return result;
        }else {
            LambdaQueryWrapper<HrBasOrganization> hr = new LambdaQueryWrapper();
            hr.eq(HrBasOrganization::getZzjc,yxdyglMainExp.getZzbz().trim());
            Long count = hrBasOrganizationService.count(hr);
            if (count == 0){
                result.setSuccess(false);
                result.setMsg("所属支行不存在");
                return result;
            }
        }




        if (value == null) {
            return result;
        }
        return result;
    }
}
