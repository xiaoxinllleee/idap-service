package org.cmms.modules.tjfx.wgtjfx.wghztj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.tjfx.wgtjfx.wghztj.entity.Wghztj;

/**
 * @Description: 网格汇总统计
 * @Author: jeecg-boot
 * @Date:   2022-01-07
 * @Version: V1.0
 */
public interface IWghztjService extends IService<Wghztj> {
    Wghztj getLatestInfo(String wgbh);
}
