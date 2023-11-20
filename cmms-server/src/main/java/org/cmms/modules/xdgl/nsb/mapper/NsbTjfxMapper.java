package org.cmms.modules.xdgl.nsb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.xdgl.nsb.entity.NsbTjfx;

/**
 * @Description: 年审统计分析
 * @Author: jeecg-boot
 * @Date:   2023-07-04
 * @Version: V1.0
 */
public interface NsbTjfxMapper extends BaseMapper<NsbTjfx> {

    void tq();
}
