package org.cmms.modules.tjbb.ckywfx.dqfdtj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.ckywfx.dqfdtj.entity.Dqfdtj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 定期分段统计
 * @Author: jeecg-boot
 * @Date:   2021-08-20
 * @Version: V1.0
 */
public interface DqfdtjMapper extends BaseMapper<Dqfdtj> {
    void pDqfdtj(@Param("tjyf")String tjyf);
}
