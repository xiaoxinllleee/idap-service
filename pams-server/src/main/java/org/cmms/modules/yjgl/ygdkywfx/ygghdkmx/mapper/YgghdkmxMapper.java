package org.cmms.modules.yjgl.ygdkywfx.ygghdkmx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.yjgl.ygdkywfx.ygghdkmx.entity.Ygghdkmx;

/**
 * @Description: 员工管户贷款明细
 * @Author: jeecg-boot
 * @Date:   2023-07-13
 * @Version: V1.0
 */
public interface YgghdkmxMapper extends BaseMapper<Ygghdkmx> {
    void init(@Param("ksrq")String ksrq, @Param("jsrq")String jsrq, @Param("jgdm")String jgdm, @Param("yggh")String yggh);
}
