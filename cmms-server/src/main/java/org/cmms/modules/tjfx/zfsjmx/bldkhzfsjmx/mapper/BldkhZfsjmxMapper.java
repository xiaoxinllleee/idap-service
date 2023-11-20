package org.cmms.modules.tjfx.zfsjmx.bldkhzfsjmx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.tjfx.zfsjmx.bldkhzfsjmx.entity.BldkhZfsjmx;

/**
 * @Description: 不良贷款户走访数据明细
 * @Author: jeecg-boot
 * @Date:   2022-06-29
 * @Version: V1.0
 */
public interface BldkhZfsjmxMapper extends BaseMapper<BldkhZfsjmx> {
    void init(String tjrq);
}
