package org.cmms.modules.yjgl.ygckywfx.ygzhckmx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.yjgl.ygckywfx.ygzhckmx.entity.Ygzhckmx;

/**
 * @Description: 员工综合存款明细
 * @Author: jeecg-boot
 * @Date:   2023-07-11
 * @Version: V1.0
 */
public interface YgzhckmxMapper extends BaseMapper<Ygzhckmx> {
    void init(@Param("ksrq")String ksrq, @Param("jsrq")String jsrq, @Param("jgdm")String jgdm, @Param("yggh")String yggh);
}
