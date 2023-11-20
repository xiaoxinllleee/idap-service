package org.cmms.modules.ywgl.cdkfx.zhxzbldkhz.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.ywgl.cdkfx.zhxzbldkhz.entity.ErpJxkhZhxzbldkhz;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 支行新增不良贷款汇总
 * @Author: jeecg-boot
 * @Date:   2021-06-24
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IErpJxkhZhxzbldkhzService extends IService<ErpJxkhZhxzbldkhz> {
    void pJxkhZhxzbldkhz(String tjyf);
}
