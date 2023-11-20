package org.cmms.modules.khlc.grgpgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasStaffPostVo;
import org.cmms.modules.khlc.grgpgl.entity.Grgpgl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 个人挂片管理
 * @Author: jeecg-boot
 * @Date:   2023-03-09
 * @Version: V1.0
 */
public interface GrgpglMapper extends BaseMapper<Grgpgl> {
    List<HrBasStaffPostVo> getListClaim(@Param("zzbz")String zzbz,@Param("yggh")String yggh,@Param("ygxm")String ygxm);

}
