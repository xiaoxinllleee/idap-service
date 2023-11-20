package org.cmms.modules.tjfx.jgtjfx.jghztj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.tjfx.jgtjfx.jghztj.entity.Jghztj;

/**
 * @Description: 机构汇总统计
 * @Author: jeecg-boot
 * @Date:   2022-01-07
 * @Version: V1.0
 */
public interface IJghztjService extends IService<Jghztj> {
    Jghztj getLatestInfo(String jgdm);
}
