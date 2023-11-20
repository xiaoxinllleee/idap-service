package org.cmms.modules.ywgl.cdkfx.xzbldkmx.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.ywgl.cdkfx.xzbldkmx.entity.MidDmpmDkyebmxqktj;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 新增不良贷款明细
 * @Author: jeecg-boot
 * @Date:   2021-06-23
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IMidDmpmDkyebmxqktjService extends IService<MidDmpmDkyebmxqktj> {
    String getByName(String custidName);
}
