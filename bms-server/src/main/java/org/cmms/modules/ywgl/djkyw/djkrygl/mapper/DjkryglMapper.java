package org.cmms.modules.ywgl.djkyw.djkrygl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ywgl.djkyw.djkrygl.entity.Djkrygl;
import org.cmms.modules.ywgl.djkyw.djkrygl.entity.HrBasStaffPostVo;

/**
 * @Description: 贷记卡人员关联
 * @Author: jeecg-boot
 * @Date:   2021-12-03
 * @Version: V1.0
 */
public interface DjkryglMapper extends BaseMapper<Djkrygl> {
    List<HrBasStaffPostVo> getListClaim(@Param("yggh")String yggh, @Param("gyh")String gyh, @Param("khjlbz")String khjlbz);
}
