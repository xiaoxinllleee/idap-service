package org.cmms.modules.tjbb.dkywfx.dkkhfxb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.dkywfx.dkkhfxb.entity.Dkkhfxb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 贷款客户分析表
 * @Author: jeecg-boot
 * @Date:   2021-08-24
 * @Version: V1.0
 */
public interface DkkhfxbMapper extends BaseMapper<Dkkhfxb> {
    void pDkkhfxb(@Param("tjyf")String tjyf);
}
