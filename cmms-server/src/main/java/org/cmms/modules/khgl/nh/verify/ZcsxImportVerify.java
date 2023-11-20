package org.cmms.modules.khgl.nh.verify;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdcardUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.khgl.nh.entity.Nhbkbpy;
import org.cmms.modules.khgl.nh.service.INhbkbpyService;
import org.cmms.modules.khgl.nh.vo.ZcsxVo;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * @author
 * @date 2022/8/26 23:19 周五
 */
@Component
public class ZcsxImportVerify implements IExcelVerifyHandler {

    @Autowired
    private INhbkbpyService nhbkbpyService;
    @Autowired
    private INhxqService nhxqService;

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
        ZcsxVo form = (ZcsxVo) var1;
        Date pysj=(Date)var3;
        form.setPysj(pysj);
        int errorNum=0;
        StringBuffer buffer=new StringBuffer();

        if (StringUtils.isBlank(form.getZjhm())){
            errorNum=+1;
            buffer.append("未填写评议对象证件号码！");
        }
        if (!IdcardUtil.isValidCard(form.getZjhm())){
            errorNum=+1;
            buffer.append("评议对象证件号码不规范！");
        }
        if (StringUtils.isBlank(form.getPyyzjhm())){
            errorNum=+1;
            buffer.append("未填写评议员证件号码！");
        }
        if (!IdcardUtil.isValidCard(form.getZjhm())){
            errorNum=+1;
            buffer.append("评议员证件号码不规范！");
        }
        if (StringUtils.isNotBlank(form.getLxdh()) && !Validator.isMobile(form.getLxdh()) ){
            errorNum=+1;
            buffer.append("手机号码不规范！");
        }
        if (errorNum>0){
            result.setSuccess(false);
            result.setMsg(buffer.toString());
            return result;
        }
        //如果该评议员已经评议过该户，则跳过
        QueryWrapper<Nhbkbpy> nhbkbpyQueryWrapper=new QueryWrapper<>();
        nhbkbpyQueryWrapper.eq("zjhm",form.getZjhm().trim());
        nhbkbpyQueryWrapper.eq("pyyzjhm",form.getPyyzjhm().trim());
        Nhbkbpy nhbkbpy1=nhbkbpyService.getOne(nhbkbpyQueryWrapper,false);
        if (nhbkbpy1!=null){
            result.setSuccess(false);
            result.setMsg("该户已被该评议员评议过！");
            return result;
        }

        //农户表中是否有记录
        QueryWrapper<Nhxq> nhxqQueryWrapper=new QueryWrapper<>();
        nhxqQueryWrapper.eq("zjhm",form.getZjhm());
        Nhxq nhxq=nhxqService.getOne(nhxqQueryWrapper,false);
        if (nhxq==null){
            result.setSuccess(false);
            result.setMsg("系统未查找到该评议对象的农户记录！");
            return result;
        }

        return result;

    }

}
