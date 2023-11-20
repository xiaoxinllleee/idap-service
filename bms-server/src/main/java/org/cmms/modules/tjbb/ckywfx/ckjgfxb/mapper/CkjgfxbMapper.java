package org.cmms.modules.tjbb.ckywfx.ckjgfxb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.ckywfx.ckjgfxb.entity.Ckjgfxb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 存款结构分析表
 * @Author: jeecg-boot
 * @Date:   2021-08-20
 * @Version: V1.0
 */
public interface CkjgfxbMapper extends BaseMapper<Ckjgfxb> {
    void pCkjgfx(@Param("tjrq")String tjrq);
}
