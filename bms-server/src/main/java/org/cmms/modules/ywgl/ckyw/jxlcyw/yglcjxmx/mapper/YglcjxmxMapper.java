package org.cmms.modules.ywgl.ckyw.jxlcyw.yglcjxmx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ywgl.ckyw.jxlcyw.yglcjxmx.entity.Yglcjxmx;

/**
 * @Description: 员工揽储绩效明细
 * @Author: jeecg-boot
 * @Date:   2021-10-27
 * @Version: V1.0
 */
public interface YglcjxmxMapper extends BaseMapper<Yglcjxmx> {
    void pYglcjxmx(@Param("tjyf")String tjyf);
}
