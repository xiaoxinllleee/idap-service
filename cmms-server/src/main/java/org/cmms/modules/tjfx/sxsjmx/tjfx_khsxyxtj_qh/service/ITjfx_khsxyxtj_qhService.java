package org.cmms.modules.tjfx.sxsjmx.tjfx_khsxyxtj_qh.service;

import org.cmms.modules.tjfx.sxsjmx.tjfx_khsxyxtj_qh.entity.Tjfx_khsxyxtj_qh;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-19
 * @Version: V1.0
 */
public interface ITjfx_khsxyxtj_qhService extends IService<Tjfx_khsxyxtj_qh> {
    public void extract(String tjyf);
}
