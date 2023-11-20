package org.cmms.modules.tjfx.wgtjfx.bkbpytjzh.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.tjfx.wgtjfx.bkbpytjzh.entity.BkbpytjZh;

/**
 * @Description: 背靠背评议统计_支行
 * @Author: jeecg-boot
 * @Date:   2022-05-09
 * @Version: V1.0
 */
public interface BkbpytjZhMapper extends BaseMapper<BkbpytjZh> {
    Date getMaxTjrq();
}
