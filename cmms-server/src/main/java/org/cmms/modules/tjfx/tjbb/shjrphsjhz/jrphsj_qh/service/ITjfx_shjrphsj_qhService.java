package org.cmms.modules.tjfx.tjbb.shjrphsjhz.jrphsj_qh.service;

import org.cmms.modules.tjfx.tjbb.shjrphsjhz.jrphsj_qh.entity.Tjfx_shjrphsj_qh;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 全行金融普惠数据
 * @Author: jeecg-boot
 * @Date:   2020-11-05
 * @Version: V1.0
 */
public interface ITjfx_shjrphsj_qhService extends IService<Tjfx_shjrphsj_qh> {
    void InitDataToQh(Map<String, String> sql);

}
