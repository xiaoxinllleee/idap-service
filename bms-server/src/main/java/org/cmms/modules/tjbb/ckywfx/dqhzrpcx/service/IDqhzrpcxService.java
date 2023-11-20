package org.cmms.modules.tjbb.ckywfx.dqhzrpcx.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.ckywfx.dqhzrpcx.entity.Dqhzrpcx;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 定期汇总日平查询
 * @Author: jeecg-boot
 * @Date:   2021-08-20
 * @Version: V1.0
 */
@DS("tjbb")//tjbb
public interface IDqhzrpcxService extends IService<Dqhzrpcx> {
    void pDqhzrpcx(String tjyf);
}
