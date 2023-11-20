package org.cmms.modules.tjfx.jcsjgl.cssz.service;

import org.cmms.modules.tjfx.jcsjgl.cssz.entity.TjfxCssz;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 参数设置
 * @Author: jeecg-boot
 * @Date:   2020-03-25
 * @Version: V1.0
 */
public interface ITjfxCsszService extends IService<TjfxCssz> {

    String queryCszByCsbm(String csbm);

}
