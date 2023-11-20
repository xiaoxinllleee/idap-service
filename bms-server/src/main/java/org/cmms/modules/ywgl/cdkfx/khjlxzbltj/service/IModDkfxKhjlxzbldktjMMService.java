package org.cmms.modules.ywgl.cdkfx.khjlxzbltj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.ywgl.cdkfx.khjlxzbltj.entity.ModDkfxKhjlxzbldktjMM;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 客户经理新增不良统计
 * @Author: jeecg-boot
 * @Date:   2021-06-21
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IModDkfxKhjlxzbldktjMMService extends IService<ModDkfxKhjlxzbldktjMM> {
    /**
     * 根据名字获取客户经理标识
     * @param ygxm
     * @return
     */
    String getCustidByName(String ygxm);
}
