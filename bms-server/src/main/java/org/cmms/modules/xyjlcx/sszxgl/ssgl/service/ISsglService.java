package org.cmms.modules.xyjlcx.sszxgl.ssgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.xyjlcx.sszxgl.ssgl.entity.Ssgl;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 诉讼管理
 * @Author: jeecg-boot
 * @Date:   2021-08-16
 * @Version: V1.0
 */
@DS("zx") // zx
public interface ISsglService extends IService<Ssgl> {
    @DS("eweb")
    void pSsgl();
}
