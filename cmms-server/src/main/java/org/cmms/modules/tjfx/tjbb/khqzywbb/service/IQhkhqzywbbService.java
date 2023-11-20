package org.cmms.modules.tjfx.tjbb.khqzywbb.service;

import org.cmms.modules.tjfx.tjbb.khqzywbb.entity.Qhkhqzywbb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 客户潜在业务报表(全行)
 * @Author: jeecg-boot
 * @Date:   2020-04-01
 * @Version: V1.0
 */
public interface IQhkhqzywbbService extends IService<Qhkhqzywbb> {
    public void extract();
}
