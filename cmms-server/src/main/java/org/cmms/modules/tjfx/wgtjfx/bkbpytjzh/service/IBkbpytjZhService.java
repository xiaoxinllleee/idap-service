package org.cmms.modules.tjfx.wgtjfx.bkbpytjzh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.tjfx.wgtjfx.bkbpytjzh.entity.BkbpytjZh;

import java.util.Date;

/**
 * @Description: 背靠背评议统计_支行
 * @Author: jeecg-boot
 * @Date:   2022-05-09
 * @Version: V1.0
 */
public interface IBkbpytjZhService extends IService<BkbpytjZh> {
    Date getMaxTjrq();
}
