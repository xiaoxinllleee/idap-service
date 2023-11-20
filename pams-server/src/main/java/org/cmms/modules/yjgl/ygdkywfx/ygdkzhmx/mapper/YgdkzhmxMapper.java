package org.cmms.modules.yjgl.ygdkywfx.ygdkzhmx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.yjgl.ygdkywfx.ygdkzhmx.entity.Ygdkzhmx;

/**
 * @Description: 员工贷款综合明细
 * @Author: jeecg-boot
 * @Date:   2023-07-13
 * @Version: V1.0
 */
public interface YgdkzhmxMapper extends BaseMapper<Ygdkzhmx> {
    void init(@Param("ksrq")String ksrq, @Param("jsrq")String jsrq, @Param("jgdm")String jgdm, @Param("yggh")String yggh);
}
