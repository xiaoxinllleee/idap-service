package org.cmms.modules.tjbb.dkywfx.qhdkjgfxb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.tjbb.dkywfx.qhdkjgfxb.entity.Qhdkjgfxb;

/**
 * @Description: 全行贷款结构分析表
 * @Author: jeecg-boot
 * @Date:   2021-10-20
 * @Version: V1.0
 */
public interface QhdkjgfxbMapper extends BaseMapper<Qhdkjgfxb> {
    void pDkjgfxb(@Param("tjrq")String tjrq);
}
