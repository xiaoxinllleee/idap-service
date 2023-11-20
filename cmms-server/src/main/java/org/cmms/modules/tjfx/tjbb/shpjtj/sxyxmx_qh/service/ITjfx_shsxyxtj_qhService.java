package org.cmms.modules.tjfx.tjbb.shpjtj.sxyxmx_qh.service;

import org.cmms.modules.tjfx.tjbb.shpjtj.sxyxmx_qh.entity.Tjfx_shsxyxtj_qh;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 全行授信用信统计
 * @Author: jeecg-boot
 * @Date:   2020-11-05
 * @Version: V1.0
 */
public interface ITjfx_shsxyxtj_qhService extends IService<Tjfx_shsxyxtj_qh> {
    void InitDataToQh(Map<String, String> sql);

}
