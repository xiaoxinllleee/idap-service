package org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service;

import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.entity.TjfxZhby;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 支行统计表一
 * @Author: cmms
 * @Date:   2019-12-08
 * @Version: V1.0
 */
public interface ITjfxZhbyService extends IService<TjfxZhby> {

    public  void extract(Map<String,String> sql);

    String queryTableDictTextByKey(String table, String text, String code, String key);

}
