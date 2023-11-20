package org.cmms.modules.tjbb.ckywfx.dqhzrpcx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.ckywfx.dqhzrpcx.entity.Dqhzrpcx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 定期汇总日平查询
 * @Author: jeecg-boot
 * @Date:   2021-08-20
 * @Version: V1.0
 */
public interface DqhzrpcxMapper extends BaseMapper<Dqhzrpcx> {
    void pDqhzrpcx(@Param("tjyf")String tjyf);
}
