package org.cmms.modules.tjfx.zfsjmx.bldkhzfsjmx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.tjfx.zfsjmx.bldkhzfsjmx.entity.BldkhZfsjmx;

/**
 * @Description: 不良贷款户走访数据明细
 * @Author: jeecg-boot
 * @Date:   2022-06-29
 * @Version: V1.0
 */
public interface IBldkhZfsjmxService extends IService<BldkhZfsjmx> {
    void init(String tjrq);
}
