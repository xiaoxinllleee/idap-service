package org.cmms.modules.dklldj.tjfxgl.wdsftj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dklldj.tjfxgl.wdsftj.entity.Wdsftj;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 网点上浮统计
 * @Author: jeecg-boot
 * @Date:   2020-03-06
 * @Version: V1.0
 */
@DS("rate") // rate
public interface IWdsftjService extends IService<Wdsftj> {

    @DS("eweb")
    void init();

}
