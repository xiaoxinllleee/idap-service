package org.cmms.modules.tjfx.xxwzdtj.service;

import org.cmms.modules.tjfx.xxwzdtj.entity.Tjfxkhxxwzd;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 客户信息完整度统计
 * @Author: cmms
 * @Date:   2019-09-19
 * @Version: V1.0
 */
public interface ITjfxkhxxwzdService extends IService<Tjfxkhxxwzd> {

    public void extract(String tjyf);

}
