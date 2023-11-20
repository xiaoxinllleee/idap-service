package org.cmms.modules.ywgl.cdkfx.qhdkkhckhbl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.ywgl.cdkfx.qhdkkhckhbl.entity.ModCdkfxQhdkkhckhbl;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 全行贷款客户存款回报率
 * @Author: jeecg-boot
 * @Date:   2021-07-02
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IModCdkfxQhdkkhckhblService extends IService<ModCdkfxQhdkkhckhbl> {
    void pDkfxDkkhckhbl(String tjyf);
}
