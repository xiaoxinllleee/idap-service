package org.cmms.modules.yjgl.ygckywfx.ygtzckmx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.yjgl.ygckywfx.ygtzckmx.entity.Ygtzckmx;

/**
 * @Description: 员工拓展存款明细
 * @Author: jeecg-boot
 * @Date:   2023-07-11
 * @Version: V1.0
 */
public interface YgtzckmxMapper extends BaseMapper<Ygtzckmx> {
    void init(@Param("ksrq")String ksrq,@Param("jsrq")String jsrq,@Param("jgdm")String jgdm,@Param("yggh")String yggh);
}
