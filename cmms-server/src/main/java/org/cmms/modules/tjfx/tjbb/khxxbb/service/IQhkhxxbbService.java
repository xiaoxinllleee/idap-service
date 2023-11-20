package org.cmms.modules.tjfx.tjbb.khxxbb.service;

import org.cmms.modules.tjfx.tjbb.khxxbb.entity.Qhkhxxbb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 客户信息报表(全行)
 * @Author: jeecg-boot
 * @Date:   2020-03-30
 * @Version: V1.0
 */
public interface IQhkhxxbbService extends IService<Qhkhxxbb> {
    public void extract(String tjyf);
}
