package org.cmms.modules.tjbb.ckywfx.qhckjgfxb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.tjbb.ckywfx.qhckjgfxb.entity.Qhckjgfxb;

/**
 * @Description: 全行存款结构分析表
 * @Author: jeecg-boot
 * @Date:   2021-10-20
 * @Version: V1.0
 */
public interface QhckjgfxbMapper extends BaseMapper<Qhckjgfxb> {
    void pQhckjgfxb(@Param("tjrq")String tjrq);
}
