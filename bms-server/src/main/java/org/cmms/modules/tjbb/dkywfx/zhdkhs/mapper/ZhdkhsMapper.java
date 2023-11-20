package org.cmms.modules.tjbb.dkywfx.zhdkhs.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.dkywfx.zhdkhs.entity.Zhdkhs;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 支行贷款户数
 * @Author: jeecg-boot
 * @Date:   2021-08-23
 * @Version: V1.0
 */
public interface ZhdkhsMapper extends BaseMapper<Zhdkhs> {
    void pZhdkhs(@Param("tjyf")String tjyf);
}
