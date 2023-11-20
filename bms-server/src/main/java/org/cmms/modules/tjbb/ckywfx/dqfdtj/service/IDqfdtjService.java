package org.cmms.modules.tjbb.ckywfx.dqfdtj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.ckywfx.dqfdtj.entity.Dqfdtj;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 定期分段统计
 * @Author: jeecg-boot
 * @Date:   2021-08-20
 * @Version: V1.0
 */
@DS("tjbb")//tjbb
public interface IDqfdtjService extends IService<Dqfdtj> {
    void pDqfdtj(String tjyf);
}
