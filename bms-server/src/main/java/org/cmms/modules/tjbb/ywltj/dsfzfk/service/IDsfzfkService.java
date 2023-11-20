package org.cmms.modules.tjbb.ywltj.dsfzfk.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.ywltj.dsfzfk.entity.Dsfzfk;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 第三方支付卡
 * @Author: jeecg-boot
 * @Date:   2021-08-25
 * @Version: V1.0
 */
@DS("tjbb")//tjbb
public interface IDsfzfkService extends IService<Dsfzfk> {
    void pDsfzfk(String tjyf);
}
