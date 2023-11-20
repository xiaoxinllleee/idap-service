package org.cmms.modules.ywgl.ywl.ywltz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ywgl.ywl.ywltz.entity.HrBasStaffPostVo;
import org.cmms.modules.ywgl.ywl.ywltz.entity.Ywltz;

/**
 * @Description: 业务量调整
 * @Author: jeecg-boot
 * @Date:   2021-09-29
 * @Version: V1.0
 */
public interface YwltzMapper extends BaseMapper<Ywltz> {
    List<HrBasStaffPostVo> getListClaim(@Param("ywjgdm")String ywjgdm,@Param("rglx")String rglx,
                                        @Param("gwbz")String gwbz,@Param("khjlbz")String khjlbz,
                                        @Param("yggh")String yggh);
}
