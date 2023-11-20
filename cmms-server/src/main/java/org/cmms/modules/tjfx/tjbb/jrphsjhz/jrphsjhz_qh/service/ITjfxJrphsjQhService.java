package org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphsjhz_qh.service;

import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphsjhz_qh.entity.TjfxJrphsjQh;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 金融普惠数据汇总
 * @Author: jeecg-boot
 * @Date:   2020-08-14
 * @Version: V1.0
 */
public interface ITjfxJrphsjQhService extends IService<TjfxJrphsjQh> {
    public  void extract(String tjyf);

}
