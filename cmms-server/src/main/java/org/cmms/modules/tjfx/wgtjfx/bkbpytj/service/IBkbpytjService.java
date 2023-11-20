package org.cmms.modules.tjfx.wgtjfx.bkbpytj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.tjfx.wgtjfx.bkbpytj.entity.Bkbpytj;

import java.util.Date;

/**
 * @Description: 背靠背评议统计
 * @Author: jeecg-boot
 * @Date:   2022-04-18
 * @Version: V1.0
 */
public interface IBkbpytjService extends IService<Bkbpytj> {
    Date getMaxTjrq();
}
