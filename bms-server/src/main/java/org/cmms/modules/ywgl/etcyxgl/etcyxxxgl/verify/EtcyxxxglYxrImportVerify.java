package org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.verify;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import org.cmms.modules.hr.yggl.ygrggl.service.IHrBasStaffPostService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.entity.Etcyxxxgl;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.service.IEtcyxxxglService;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.vo.EtcyxxxglYxrImport;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class EtcyxxxglYxrImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IEtcyxxxglService etcyxxxglService;
    @Autowired
    private IHrBasOrganizationService hrBasOrganizationService;
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
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        EtcyxxxglYxrImport etcyxxxglImport = (EtcyxxxglYxrImport) var1;
        String yxjgdm = (String) var3;
        Date tjyf = etcyxxxglService.getMaxTjyf();
        //
        LambdaQueryWrapper<Etcyxxxgl> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Etcyxxxgl::getKhsfzh, etcyxxxglImport.getKhsfzh());
        queryWrapper.eq(Etcyxxxgl::getKhrq, etcyxxxglImport.getKhrq());
        queryWrapper.eq(Etcyxxxgl::getKhsj, etcyxxxglImport.getKhsj());
        queryWrapper.orderByDesc(Etcyxxxgl::getTjyf);
        List<Etcyxxxgl> list = etcyxxxglService.list(queryWrapper);
        if (tjyf != null && !list.isEmpty()) {
            Etcyxxxgl etcyxxxglUpdate = list.get(0);
            try {
                Date tjyfYc = DateUtil.getFirstday_Month(tjyf, 0);
                Date tjyfYm = DateUtil.getLastday_Month(tjyf, 0);
                //导入营销人时 ，如果存在有营销人,且权限不是总行权限,则不进行数据修改
                if(!"1".equals(loginUser.getOrgCode())) {
                    if(!StringUtils.isEmpty(etcyxxxglUpdate.getYywdjgm())) {
                        //查询是否夸机构移交，只有总行可以夸机构移交 本机构判断需要包括支行下辖分理处
                        HrBasOrganization hrBasOrganization = hrBasOrganizationService.queryByYwjgdm(etcyxxxglUpdate.getYywdjgm());
                        if (hrBasOrganization != null) {
                            if("1".equals(hrBasOrganization.getSjzzbz())) {
                                if(!etcyxxxglUpdate.getYywdjgm().equals(loginUser.getOrgCode())) {
                                    result.setSuccess(false);
                                    result.setMsg("没有权限： 只能更新办理机构是本机构的数据！");
                                    return result;
                                }
                            } else {
                                HrBasOrganization sjzz = hrBasOrganizationService.queryByZzbz(hrBasOrganization.getSjzzbz());
                                if (sjzz != null) {
                                    if(!loginUser.getOrgCode().equals(sjzz.getYwjgdm())) {
                                        result.setSuccess(false);
                                        result.setMsg("没有权限： 只能更新办理机构是本机构的数据！");
                                        return result;
                                    }
                                }
                            }
                        } else {
                            result.setSuccess(false);
                            result.setMsg("营销机构代码不正确！");
                            return result;
                        }
                    }
                    if(!(etcyxxxglImport.getKhrq().getTime() >= tjyfYc.getTime()
                            && etcyxxxglImport.getKhrq().getTime() <= tjyfYm.getTime())) {
                        result.setSuccess(false);
                        result.setMsg("没有权限： 只能更新系统内ETC数据最近一个月度的数据！");
                        return result;
                    }
                    if("线上".equals(etcyxxxglUpdate.getBlqd())) {
                        result.setSuccess(false);
                        result.setMsg("没有权限： 只能更新线下办理的营销人信息！");
                        return result;
                    }
                    if(!StringUtils.isEmpty(etcyxxxglUpdate.getYxrgh())) {
                        result.setSuccess(false);
                        result.setMsg("没有权限： 该数据已存在营销人，不能修改！");
                        return result;
                    }
                }

                if(!("无".equals(etcyxxxglImport.getYxrgh()) && "无".equals(yxjgdm))) {
                    //查询营销人是否存在
                    QueryWrapper<HrBasStaffPost> staffPostQueryWrapper = new QueryWrapper<>();
                    staffPostQueryWrapper.eq("yggh", etcyxxxglImport.getYxrgh());
                    staffPostQueryWrapper.le("rgrq", DateUtil.getFirstday_Month(new Date(), 0));
                    staffPostQueryWrapper.apply("  (lgrq is null or lgrq >= {0} )", DateUtil.getLastday_Month(new Date(), 0));
                    List<HrBasStaffPost> hrBasStaffPostList = hrBasStaffPostService.list(staffPostQueryWrapper);
                    if (!hrBasStaffPostList.isEmpty()) {
                        HrBasOrganization basOrganization = hrBasOrganizationService.queryByYwjgdm(yxjgdm);
                        if (basOrganization == null) {
                            result.setSuccess(false);
                            result.setMsg("营销机构代码不正确！");
                            return result;
                        }
                    } else {
                        result.setSuccess(false);
                        result.setMsg("营销人工号不正确，或营销人不存在岗位信息！");
                        return result;
                    }
                }
                LambdaUpdateWrapper<Etcyxxxgl> updateWrapper = new LambdaUpdateWrapper<>();
                updateWrapper.eq(Etcyxxxgl::getKhsfzh, etcyxxxglImport.getKhsfzh());
                updateWrapper.eq(Etcyxxxgl::getKhrq, etcyxxxglImport.getKhrq());
                updateWrapper.eq(Etcyxxxgl::getKhsj, etcyxxxglImport.getKhsj());
                updateWrapper.set(Etcyxxxgl::getYxrgh, etcyxxxglImport.getYxrgh());
                updateWrapper.set(Etcyxxxgl::getYxjgdm, yxjgdm);
                updateWrapper.set(Etcyxxxgl::getXgr, loginUser.getUsername());
                updateWrapper.set(Etcyxxxgl::getXgsj, new Date());
                etcyxxxglService.update(updateWrapper);
                result.setSuccess(true);
                result.setMsg("更新成功！");
            } catch (Exception e) {
                e.printStackTrace();
                result.setSuccess(false);
                result.setMsg("系统异常！");
                return result;
            }
        } else {
            result.setSuccess(false);
            result.setMsg("该客户不存在ETC信息！");
        }
        return result;
    }
}
