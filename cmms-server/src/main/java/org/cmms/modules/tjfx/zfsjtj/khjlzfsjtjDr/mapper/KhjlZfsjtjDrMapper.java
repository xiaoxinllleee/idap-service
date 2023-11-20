package org.cmms.modules.tjfx.zfsjtj.khjlzfsjtjDr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.zfsjtj.khjlzfsjtjDr.entity.KhjlZfsjtjDr;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.tjfx.zfsjtj.khjlzfsjtjDr.entity.KhjlZfsjtjDrVo;

/**
 * @Description: 客户经理走访统计-当日
 * @Author: jeecg-boot
 * @Date:   2023-05-05
 * @Version: V1.0
 */
public interface KhjlZfsjtjDrMapper extends BaseMapper<KhjlZfsjtjDr> {
    public void initKhjlZf(String yggh);
    Integer getWxzfxx(String yggh);
    KhjlZfsjtjDrVo getBthzfxx(String sszh);
    KhjlZfsjtjDrVo getYxzfHj(String yggh);
}
