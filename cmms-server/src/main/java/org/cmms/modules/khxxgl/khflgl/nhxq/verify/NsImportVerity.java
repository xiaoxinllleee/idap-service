package org.cmms.modules.khxxgl.khflgl.nhxq.verify;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.lettuce.core.ScriptOutputType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.NsImportVO;
import org.cmms.modules.system.service.ISysDictService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class NsImportVerity implements IExcelVerifyHandler {
    @Autowired
    IHrBasStaffService hrBasStaffService;
    @Autowired
    ISysDictService sysDictService;

    @Override
    public String[] getNeedVerifyFields() {
        return new String[0];
    }

    @Override
    public void setNeedVerifyFields(String[] strings) {

    }

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object o, String s, Object o1) {
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        NsImportVO nsImportVO = (NsImportVO) o;
        System.out.println(s);
        System.out.println(o1);
        log.info("===年审校验数据{}===", nsImportVO.toString());

        if (StringUtils.isBlank(nsImportVO.getSszh())) {
            result.setSuccess(false);
            result.setMsg("所属支行不能为空！");
            return result;
        }

        if (StringUtils.isBlank(nsImportVO.getKhmc())) {
            result.setSuccess(false);
            result.setMsg("姓名不能为空！");
            return result;
        }


        if (StringUtils.isBlank(nsImportVO.getZjhm())) {
            result.setSuccess(false);
            result.setMsg("身份证号码不能为空！");
            return result;
        }

        if (StringUtils.isBlank(nsImportVO.getSjhm())) {
            result.setSuccess(false);
            result.setMsg("联系电话为空！");
            return result;
        }

        if (StringUtils.isBlank(nsImportVO.getZzpddj())) {
            result.setSuccess(false);
            result.setMsg("新复评等级不能为空！");
            return result;
        }

        if (StringUtils.isBlank(nsImportVO.getCpzl())) {
            result.setSuccess(false);
            result.setMsg("信用产品不能为空！");
            return result;
        }

//        if (StringUtils.isBlank(nsImportVO.getSskhjl())) {
//            result.setSuccess(false);
//            result.setMsg("所属客户经理工号不能为空！");
//            return result;
//        } else {
//            HrBasStaff byId = hrBasStaffService.getById(nsImportVO.getSskhjl());
//            if (byId == null) {
//                result.setSuccess(false);
//                result.setMsg("所属客户经理工号不存在！");
//                return result;
//            }
//        }

//        if ("新复评授信".equals(s)){
//            if (StringUtils.isBlank(s)){
//                result.setSuccess(false);
//                result.setMsg("新复评授信不能为空！");
//                return result;
//            }else {
//                System.out.println(s);
//                if (!StringUtils.isNumeric(o1.toString())){
//                    result.setSuccess(false);
//                    result.setMsg("新复评授信只能填写数字！");
//                    return result;
//                }
//            }
//        }

        if (StringUtils.isBlank(nsImportVO.getZzsxed())) {
            result.setSuccess(false);
            result.setMsg("新复评授信不能为空！");
            return result;
        } else {
            if (!StringUtils.isNumeric(nsImportVO.getZzsxed())) {
                result.setSuccess(false);
                result.setMsg("新复评授信只能填写数字！");
                return result;
            }
        }

        if ("所属客户经理工号".equals(s)) {
            System.out.println(o1.toString());
            if (StringUtils.isBlank(o1.toString())) {
                result.setSuccess(false);
                result.setMsg("所属客户经理工号不能为空！");
                return result;
            } else {
                HrBasStaff byId = hrBasStaffService.getById(o1.toString());
                if (byId == null) {
                    result.setSuccess(false);
                    result.setMsg("所属客户经理工号不存在！");
                    return result;
                }
            }
        }


        String zzpddj = nsImportVO.getZzpddj();
        BigDecimal zzsxed = new BigDecimal(nsImportVO.getZzsxed());

        if ("A".equals(zzpddj)) {
                if (nsImportVO.getCpzl().equals("6") ||
                        nsImportVO.getCpzl().equals("13")
                        || nsImportVO.getCpzl().equals("14")) {

                    if (zzsxed.compareTo(new BigDecimal("50")) > 0) {
                        result.setSuccess(false);
                        result.setMsg("信用产品额度不能超过50万！");
                        return result;
                    }
                } else if (nsImportVO.getCpzl().equals("7")) {
                    if (zzsxed.compareTo(new BigDecimal("40")) > 0) {
                        result.setSuccess(false);
                        result.setMsg("信用产品额度不能超过40万！");
                        return result;
                    }
                } else {
                    if (zzsxed.compareTo(new BigDecimal("30")) > 0) {
                        result.setSuccess(false);
                        result.setMsg("A级不能超过30万！");
                        return result;
                    }
                }
        }
        if ("B".equals(zzpddj) && zzsxed.compareTo(new BigDecimal("20")) > 0) {
            result.setSuccess(false);
            result.setMsg("B级不能超过20万！");
            return result;
        }
        if ("C".equals(zzpddj) && zzsxed.compareTo(new BigDecimal("10")) > 0) {
            result.setSuccess(false);
            result.setMsg("C级不能超过10万！");
            return result;
        }
        if ("D".equals(zzpddj) && zzsxed.compareTo(new BigDecimal("5")) > 0) {
            result.setSuccess(false);
            result.setMsg("D级不能超过5万！");
            return result;
        }
        if ("E".equals(zzpddj) && zzsxed.compareTo(new BigDecimal("0")) > 0) {
            result.setSuccess(false);
            result.setMsg("E级不能超过0万！");
            return result;
        }

        result.setSuccess(true);
        result.setMsg("成功");

        return result;
    }
}
