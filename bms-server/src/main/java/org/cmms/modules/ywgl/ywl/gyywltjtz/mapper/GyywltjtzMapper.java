package org.cmms.modules.ywgl.ywl.gyywltjtz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ywgl.ywl.gyywltjtz.entity.Gyywltjtz;

/**
 * @Description: 柜员业务量统计调整
 * @Author: jeecg-boot
 * @Date:   2021-09-30
 * @Version: V1.0
 */
public interface GyywltjtzMapper extends BaseMapper<Gyywltjtz> {
    void pGyywltjtz(@Param("tjyf")String tjyf);
}
