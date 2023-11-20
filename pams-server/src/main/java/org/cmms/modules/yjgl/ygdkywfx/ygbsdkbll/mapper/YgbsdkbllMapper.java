package org.cmms.modules.yjgl.ygdkywfx.ygbsdkbll.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.yjgl.ygdkywfx.ygbsdkbll.entity.Ygbsdkbll;

/**
 * @Description: 员工包收贷款不良率
 * @Author: jeecg-boot
 * @Date:   2023-07-13
 * @Version: V1.0
 */
public interface YgbsdkbllMapper extends BaseMapper<Ygbsdkbll> {
    void init(@Param("tjyf")String tjrf,@Param("jgdm")String jgdm,@Param("yggh")String yggh);

}
