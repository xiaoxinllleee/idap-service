package org.cmms.modules.tjbb.gxywqkb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.gxywqkb.entity.Gxywqkb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 各项业务情况表
 * @Author: Penghr
 * @Date:   2022-12-12
 * @Version: V1.0
 */
public interface GxywqkbMapper extends BaseMapper<Gxywqkb> {

    Gxywqkb queryByDatadateAndXh(@Param("data_date") String data_date,@Param("xh") String xh);

}
