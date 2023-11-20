package org.cmms.modules.dklldj.csszgl.zxllpzgl.verify;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.RedisUtil;
import org.cmms.modules.dklldj.csszgl.zxllpzgl.entity.Zxllpzgl;
import org.cmms.modules.dklldj.csszgl.zxllpzgl.vo.ZxllpzglVO;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ZxllpzglVerify implements IExcelVerifyHandler {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void setNeedVerifyFields(String[] arr) {

    }

    @Override
    public String[] getNeedVerifyFields() {
        return new String[0];
    }

    /**
     *
     * @param obj 当前对象
     * @param name 当前字段名称
     * @param value 当前值
     * @return
     */
    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object obj, String name, Object value) {
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
        ZxllpzglVO table = (ZxllpzglVO) obj;
        table.setQydm(qydm);
        table.setLrbz(0);
        table.setLrr(loginUser.getUsername());
        table.setLrsj(new Date());

        UpdateWrapper<Zxllpzgl> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("xddkpz",table.getXddkpz());
        updateWrapper.eq("dkqx",table.getDkqx());
        updateWrapper.eq("zhpcdf_begin",table.getZhpcdfBegin());
        updateWrapper.eq("zhpcdf_end",table.getZhpcdfEnd());

        return result;
    }
}
