package org.cmms.modules.xddagl.tjfx.dkdalrqkb.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.xddagl.tjfx.dkdalrqkb.entity.Dkdalrqkb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 贷款档案录入情况表
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
@DS("eweb")
public interface IDkdalrqkbService extends IService<Dkdalrqkb> {
    public void pDkdalrqkb();
}
