package org.cmms.modules.tjfx.zftjysb.xzcysb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.tjfx.zftjysb.xzcysb.entity.ZftjysbXzc;

import java.util.Date;

/**
 * @Description: 走访统计验收表-行政村
 * @Author: jeecg-boot
 * @Date:   2021-05-26
 * @Version: V1.0
 */
public interface IZftjysbXzcService extends IService<ZftjysbXzc> {
    Date getMaxDate();
    void init();
}
