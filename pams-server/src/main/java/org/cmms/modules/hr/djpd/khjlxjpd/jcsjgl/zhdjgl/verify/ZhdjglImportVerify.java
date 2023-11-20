package org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zhdjgl.verify;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zhdjgl.entity.Zhdjgl;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zhdjgl.service.IZhdjglService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author
 * @date 2022/8/26 23:19 周五
 */
@Component
public class ZhdjglImportVerify implements IExcelVerifyHandler {

    @Autowired
    private IZhdjglService zhdjglService;

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
        Zhdjgl form = (Zhdjgl) var1;
        String djms = (String)var3;
        form.setDjms(djms);
        System.out.println(form);
        String format = DateUtil.format(form.getPdrq(), "yyyy-MM-dd");
        Date parse = DateUtil.parse(format);
        System.out.println(parse);
        QueryWrapper<Zhdjgl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("zzbz",form.getZzbz());
        queryWrapper.eq("pdzq",form.getPdzq());
        queryWrapper.eq("pdrq",parse);
        Zhdjgl zhdjgl = zhdjglService.getOne(queryWrapper, false);
        if (zhdjgl != null){
                zhdjglService.remove(queryWrapper);
        }
        form.setLrbz(0);
        form.setLrr(sysUser.getUsername());
        form.setLrsj(new Date());
        return result;

    }
}
