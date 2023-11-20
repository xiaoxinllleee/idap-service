package org.cmms.modules.performance.depositcustomer.ckkhxxgl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.cmms.modules.performance.depositcustomer.ckkhghlsb.service.ICkkhghlsbService;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.vo.CkkhghlsbImp;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 彭浩然
 * @date 2023.04.11
 */
@Slf4j
@Component
public class CkkhGhxxImportVerify implements IExcelVerifyHandler {

    @Autowired
    private ICkkhghlsbService ckkhghlsbService;
    @Autowired
    private IHrBasStaffService hrBasStaffService;

    @Override
    public String[] getNeedVerifyFields() {
        return new String[0];
    }

    @Override
    public void setNeedVerifyFields(String[] arr) {}

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object obj, String name, Object value) {
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        log.info("Object obj：" + obj);
        log.info("String name：" + name);
        log.info("Object value：" + value);
        CkkhghlsbImp table = (CkkhghlsbImp) obj;

//        QueryWrapper<Ckkhghlsb> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("jgdm",table.getJgdm());
//        queryWrapper.eq("khbh",table.getKhbh());
//        Ckkhghlsb record = ckkhghlsbService.getOne(queryWrapper,false);
//        if (record == null) {
//            result.setSuccess(false);
//            result.setMsg("未找到对应的存款客户信息！");
//            return result;
//        }
//
//        QueryWrapper<HrBasStaff> queryWrapper1 = new QueryWrapper<>();
//        queryWrapper1.eq("yggh",table.getGhr());
//        HrBasStaff record1 = hrBasStaffService.getOne(queryWrapper1,false);
//        if (record1 == null) {
//            result.setSuccess(false);
//            result.setMsg("未找到对应的管户人信息！");
//            return result;
//        }

        return result;
    }
}
