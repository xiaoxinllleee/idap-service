package org.cmms.modules.tjfx.khjdfgl.service;

import org.cmms.modules.tjfx.khjdfgl.entity.Tjfx_jdfgltj;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-12-09
 * @Version: V1.0
 */
public interface ITjfx_jdfgltjService extends IService<Tjfx_jdfgltj> {
    public void extract(String tjyf);
}
