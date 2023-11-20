package org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.service;

import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.entity.TjfxZhbe;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 支行统计报表二
 * @Author: cmms
 * @Date:   2019-12-08
 * @Version: V1.0
 */
public interface ITjfxZhbeService extends IService<TjfxZhbe> {

    public void extract(Map<String,String>sql);
}
