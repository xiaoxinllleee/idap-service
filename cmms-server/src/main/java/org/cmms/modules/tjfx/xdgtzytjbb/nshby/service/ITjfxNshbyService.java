package org.cmms.modules.tjfx.xdgtzytjbb.nshby.service;

import org.cmms.modules.tjfx.xdgtzytjbb.nshby.entity.TjfxNshby;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 农商行统计表一
 * @Author: cmms
 * @Date:   2019-12-08
 * @Version: V1.0
 */
public interface ITjfxNshbyService extends IService<TjfxNshby> {

    public  void extract(Map<String,String> sql);


}
