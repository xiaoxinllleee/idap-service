package org.cmms.modules.tjfx.xdgtzytjbb.cscqzhbe.service;

import org.cmms.modules.tjfx.xdgtzytjbb.cscqzhbe.entity.TjfxCscqzhbe;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 村社村前统计支行表二
 * @Author: cmms
 * @Date:   2019-12-14
 * @Version: V1.0
 */
public interface ITjfxCscqzhbeService extends IService<TjfxCscqzhbe> {


    public void extract(String tjyf);
}
