package org.cmms.modules.khgl.khhmc.verify;

import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.common.util.BeanUtil;
import org.cmms.modules.khgl.khhmc.entity.Khfjxxgl;
import org.cmms.modules.khgl.khhmc.service.IKhfjxxglService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;


@Component
public class KhfjxxglImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IKhfjxxglService khfjxxglService;
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
        Khfjxxgl khfjxxglImport = (Khfjxxgl) var1;
        QueryWrapper<Khfjxxgl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("zjhm", khfjxxglImport.getZjhm());
        Khfjxxgl khfjxxgl = khfjxxglService.getOne(queryWrapper);
        if (khfjxxgl != null) {
            //已经存在的证件号码
            //将导入的非空字段保存到数据库对象中
            BeanUtil.copyPropertiesIgnoreNull(khfjxxglImport, khfjxxgl);
            if (var3 != null) {
                khfjxxgl.setSfzdjb((String)var3);
            }
            Field[] fields = ReflectUtil.getFields(Khfjxxgl.class);
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                ReflectUtil.setFieldValue(var1, field, ReflectUtil.getFieldValue(khfjxxgl, field));
            }




            //删除数据
            khfjxxglService.remove(queryWrapper);
        }

        return result;
    }
}
