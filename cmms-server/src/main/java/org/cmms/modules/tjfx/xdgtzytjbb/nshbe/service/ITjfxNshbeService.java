package org.cmms.modules.tjfx.xdgtzytjbb.nshbe.service;

import org.cmms.modules.tjfx.xdgtzytjbb.nshbe.entity.TjfxNshbe;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 农商行统计报表二
 * @Author: cmms
 * @Date:   2019-12-08
 * @Version: V1.0
 */
public interface ITjfxNshbeService extends IService<TjfxNshbe> {

    public void extract(String tjyf);
}
