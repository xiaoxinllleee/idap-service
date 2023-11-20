package org.cmms.modules.tjfx.wgtjfx.bkbpytj.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.tjfx.wgtjfx.bkbpytj.entity.Bkbpytj;

/**
 * @Description: 背靠背评议统计
 * @Author: jeecg-boot
 * @Date:   2022-04-18
 * @Version: V1.0
 */
public interface BkbpytjMapper extends BaseMapper<Bkbpytj> {
    Date getMaxTjrq();
}
