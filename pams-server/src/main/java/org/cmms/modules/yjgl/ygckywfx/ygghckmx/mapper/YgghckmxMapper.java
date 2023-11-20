package org.cmms.modules.yjgl.ygckywfx.ygghckmx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.yjgl.ygckywfx.ygghckmx.entity.Ygghckmx;

/**
 * @Description: 员工管户存款明细
 * @Author: jeecg-boot
 * @Date:   2023-07-11
 * @Version: V1.0
 */
public interface YgghckmxMapper extends BaseMapper<Ygghckmx> {
    void init(@Param("ksrq")String ksrq,@Param("jsrq")String jsrq,@Param("jgdm")String jgdm,@Param("yggh")String yggh);
}
