package org.cmms.modules.ywgl.ckyw.jglcmx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ywgl.ckyw.jglcmx.entity.Jglcmx;

/**
 * @Description: 机关揽储明细
 * @Author: jeecg-boot
 * @Date:   2021-10-11
 * @Version: V1.0
 */
public interface JglcmxMapper extends BaseMapper<Jglcmx> {
    void pJglcmx(@Param("tjyf")String tjyf);
}
