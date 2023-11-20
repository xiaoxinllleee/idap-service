package org.cmms.modules.ywgl.dkyw.hxdkgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ywgl.dkyw.hxdkgl.entity.HrBasStaffPostVo;
import org.cmms.modules.ywgl.dkyw.hxdkgl.entity.Hxdkgl;

/**
 * @Description: 核销贷款管理
 * @Author: jeecg-boot
 * @Date:   2021-09-23
 * @Version: V1.0
 */
public interface HxdkglMapper extends BaseMapper<Hxdkgl> {
    List<HrBasStaffPostVo> getListClaim(@Param("gyh")String gyh, @Param("khjlbz")String khjlbz, @Param("yggh")String yggh);
}
