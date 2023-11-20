package org.cmms.modules.yjgl.ygdkywfx.ygbsdkmx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.yjgl.ygdkywfx.ygbsdkmx.entity.Ygbsdkmx;

/**
 * @Description: 员工包收贷款明细
 * @Author: jeecg-boot
 * @Date:   2023-07-13
 * @Version: V1.0
 */
public interface YgbsdkmxMapper extends BaseMapper<Ygbsdkmx> {
    void init(@Param("ksrq")String ksrq, @Param("jsrq")String jsrq, @Param("jgdm")String jgdm, @Param("yggh")String yggh);
}
