package org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.verify;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.entity.Etcyxxxgl;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.service.IEtcyxxxglService;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.vo.EtcyxxxglImport;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class EtcyxxxglImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IEtcyxxxglService etcyxxxglService;
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
        EtcyxxxglImport etcyxxxglImport = (EtcyxxxglImport) var1;
        String tjyfStr = etcyxxxglImport.getTjyfStr();
        //Date tjyf = DateUtil.parseDateFormat(tjyfStr + "-01", "yyyy-MM-dd");
        String tjyfs = tjyfStr.concat("-01");
        Date tjyf = DateUtil.string2Date(tjyfs,"yyyy-MM-dd");


        //
        LambdaQueryWrapper<Etcyxxxgl> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Etcyxxxgl::getTjyf, tjyf);
        queryWrapper.eq(Etcyxxxgl::getKhsfzh, etcyxxxglImport.getKhsfzh());
        queryWrapper.eq(Etcyxxxgl::getKhrq, etcyxxxglImport.getKhrq());
        queryWrapper.eq(Etcyxxxgl::getKhsj, etcyxxxglImport.getKhsj());
        List<Etcyxxxgl> list = etcyxxxglService.list(queryWrapper);
        if (!list.isEmpty()) {
            Etcyxxxgl etcyxxxglUpdate = list.get(0);
            //数据更新
            if (!StringUtils.isEmpty(etcyxxxglImport.getBlqd())) {
                etcyxxxglUpdate.setBlqd(etcyxxxglImport.getBlqd());
            }
            if (!StringUtils.isEmpty(etcyxxxglImport.getKhxm())) {
                etcyxxxglUpdate.setKhxm(etcyxxxglImport.getKhxm());
            }
            if (!StringUtils.isEmpty(etcyxxxglImport.getKhsjh())) {
                etcyxxxglUpdate.setKhsjh(etcyxxxglImport.getKhsjh());
            }
            if (!StringUtils.isEmpty(etcyxxxglImport.getCphm())) {
                etcyxxxglUpdate.setCphm(etcyxxxglImport.getCphm());
            }
            if (!StringUtils.isEmpty(etcyxxxglImport.getCpys())) {
                etcyxxxglUpdate.setCpys(etcyxxxglImport.getCpys());
            }
            if (!StringUtils.isEmpty(etcyxxxglImport.getHkfs())) {
                etcyxxxglUpdate.setHkfs(etcyxxxglImport.getHkfs());
            }
            if (!StringUtils.isEmpty(etcyxxxglImport.getBdzkh())) {
                etcyxxxglUpdate.setBdzkh(etcyxxxglImport.getBdzkh());
            }
            if (!StringUtils.isEmpty(etcyxxxglImport.getKhjgdm())) {
                etcyxxxglUpdate.setKhjgdm(etcyxxxglImport.getKhjgdm());
            }
            if (!StringUtils.isEmpty(etcyxxxglImport.getEtcbdzt())) {
                etcyxxxglUpdate.setEtcbdzt(etcyxxxglImport.getEtcbdzt());
            }
            if (etcyxxxglImport.getXhrq() != null) {
                etcyxxxglUpdate.setXhrq(etcyxxxglImport.getXhrq());
            }
            if (etcyxxxglImport.getXhsj() != null) {
                etcyxxxglUpdate.setXhsj(etcyxxxglImport.getXhsj());
            }
            LambdaUpdateWrapper<Etcyxxxgl> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Etcyxxxgl::getTjyf, tjyf);
            updateWrapper.eq(Etcyxxxgl::getKhsfzh, etcyxxxglImport.getKhsfzh());
            updateWrapper.eq(Etcyxxxgl::getKhrq, etcyxxxglImport.getKhrq());
            updateWrapper.eq(Etcyxxxgl::getKhsj, etcyxxxglImport.getKhsj());
            etcyxxxglUpdate.setKhsfzh(null);
            etcyxxxglUpdate.setKhrq(null);
            etcyxxxglUpdate.setKhsj(null);
            etcyxxxglService.update(etcyxxxglUpdate, updateWrapper);
            result.setSuccess(false);
            result.setMsg("已经存在的数据，数据已更新！");
        } else {
            //把办理渠道是线上的把营销人改成办理人
            if("线上".equals(etcyxxxglImport.getBlqd())) {
                etcyxxxglImport.setYxrgh(etcyxxxglImport.getBlygbh());
                etcyxxxglImport.setYxjgdm(etcyxxxglImport.getYywdjgm());
            }
        }
        return result;
    }
}
