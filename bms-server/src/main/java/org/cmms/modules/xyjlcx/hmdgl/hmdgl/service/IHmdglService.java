package org.cmms.modules.xyjlcx.hmdgl.hmdgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.xyjlcx.hmdgl.hmdgl.entity.Hmdgl;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 黑名单管理
 * @Author: jeecg-boot
 * @Date:   2021-08-04
 * @Version: V1.0
 */
@DS("zx") // zx
public interface IHmdglService extends IService<Hmdgl> {
    @DS("eweb")
    void pHmdgl();
}
