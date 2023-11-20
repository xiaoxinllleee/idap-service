package org.cmms.modules.tjbb.dkywfx.dkjgfxb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.dkywfx.dkjgfxb.entity.Dkjgfxb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 贷款结构分析表
 * @Author: jeecg-boot
 * @Date:   2021-08-23
 * @Version: V1.0
 */
public interface DkjgfxbMapper extends BaseMapper<Dkjgfxb> {
    void pDkjgfxb(@Param("tjrq")String tjrq);
}
