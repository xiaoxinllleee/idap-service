package org.cmms.modules.ywgl.cdkfx.dkkhckhbl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.ywgl.cdkfx.dkkhckhbl.entity.ModCdkfxKhjldkkhckhblD;

/**
 * @Description: 贷款客户存款回报率
 * @Author: jeecg-boot
 * @Date:   2021-07-01
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IModCdkfxKhjldkkhckhblDService extends IService<ModCdkfxKhjldkkhckhblD> {
    void pDkfxDkkhckhbl(String tjyf);
}
