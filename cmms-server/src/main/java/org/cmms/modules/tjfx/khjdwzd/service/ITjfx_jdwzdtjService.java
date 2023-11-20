package org.cmms.modules.tjfx.khjdwzd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.tjfx.khjdwzd.entity.Tjfx_jdwzdtj;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-12-07
 * @Version: V1.0
 */
public interface ITjfx_jdwzdtjService extends IService<Tjfx_jdwzdtj> {
    public void extract(String tjyf);
}
