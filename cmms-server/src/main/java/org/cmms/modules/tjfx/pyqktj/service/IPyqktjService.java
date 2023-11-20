package org.cmms.modules.tjfx.pyqktj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.tjfx.pyqktj.entity.Pyqktj;

/**
 * @Description: 评议情况统计
 * @Author: jeecg-boot
 * @Date:   2022-12-30
 * @Version: V1.0
 */
public interface IPyqktjService extends IService<Pyqktj> {
    public void init(String tjrq);
}
