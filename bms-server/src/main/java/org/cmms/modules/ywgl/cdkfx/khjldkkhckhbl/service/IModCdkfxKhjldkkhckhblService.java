package org.cmms.modules.ywgl.cdkfx.khjldkkhckhbl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.ywgl.cdkfx.khjldkkhckhbl.entity.ModCdkfxKhjldkkhckhbl;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 客户经理贷款客户存款回报率
 * @Author: jeecg-boot
 * @Date:   2021-06-30
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IModCdkfxKhjldkkhckhblService extends IService<ModCdkfxKhjldkkhckhbl> {
    void pDkfxDkkhckhbl(String tjyf);

    /**
     * 根据名字获取员工工号
     * @param ygxm
     * @return
     */
    String getYgghByName(String ygxm);
}
