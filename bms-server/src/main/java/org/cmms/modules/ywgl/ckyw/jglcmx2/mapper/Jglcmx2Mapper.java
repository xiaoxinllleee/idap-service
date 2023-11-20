package org.cmms.modules.ywgl.ckyw.jglcmx2.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ywgl.ckyw.jglcmx2.entity.Jglcmx2;

/**
 * @Description: 机关揽储明细
 * @Author: jeecg-boot
 * @Date:   2021-10-11
 * @Version: V1.0
 */
public interface Jglcmx2Mapper extends BaseMapper<Jglcmx2> {
    void pJglcmx2(@Param("tjyf")String tjyf, @Param("jgdm")String jgdm, @Param("tzrgh")String tzrgh);
}
