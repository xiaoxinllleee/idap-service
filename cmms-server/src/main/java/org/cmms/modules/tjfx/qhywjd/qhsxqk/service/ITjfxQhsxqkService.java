package org.cmms.modules.tjfx.qhywjd.qhsxqk.service;

import org.cmms.modules.tjfx.qhywjd.qhsxqk.entity.TjfxQhsxqk;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 全行授信情况
 * @Author: jeecg-boot
 * @Date:   2023-08-16
 * @Version: V1.0
 */
public interface ITjfxQhsxqkService extends IService<TjfxQhsxqk> {
    void initData(String yggh);
}
