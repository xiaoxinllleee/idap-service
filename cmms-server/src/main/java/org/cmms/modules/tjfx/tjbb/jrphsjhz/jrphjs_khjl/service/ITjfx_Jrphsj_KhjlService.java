package org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphjs_khjl.service;

import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphjs_khjl.entity.TjfxJrphsjKhjl;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 客户经理金融普汇
 * @Author: jeecg-boot
 * @Date:   2020-09-07
 * @Version: V1.0
 */
public interface ITjfx_Jrphsj_KhjlService extends IService<TjfxJrphsjKhjl> {
    public  void extract(Map<String,String> sql);

    String queryTableDictTextByKey(String table, String text, String code, String key);
}
