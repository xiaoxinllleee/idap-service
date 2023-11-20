package org.cmms.modules.tjfx.xdgtzytjbb.cscqzhby.service;

import org.cmms.modules.tjfx.xdgtzytjbb.cscqzhby.entity.TjfxCscqzhby;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 村社村前统计支行表一
 * @Author: cmms
 * @Date:   2019-12-14
 * @Version: V1.0
 */
public interface ITjfxCscqzhbyService extends IService<TjfxCscqzhby> {

    public void extract(Map<String,String> sql);

}
