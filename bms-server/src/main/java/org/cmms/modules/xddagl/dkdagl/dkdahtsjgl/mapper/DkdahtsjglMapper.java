package org.cmms.modules.xddagl.dkdagl.dkdahtsjgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xddagl.dkdagl.dkdahtsjgl.entity.Dkdahtsjgl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.entity.HrBasStaffPostVo;

/**
 * @Description: 贷款合同数据管理
 * @Author: jeecg-boot
 * @Date:   2022-01-07
 * @Version: V1.0
 */
public interface DkdahtsjglMapper extends BaseMapper<Dkdahtsjgl> {
    void pDkhtsjgl();

    List<HrBasStaffPostVo> getListClaim(@Param("ywjgdm")String ywjgdm, @Param("rglx")String rglx,
                                        @Param("gwbz")String gwbz, @Param("khjlbz")String khjlbz,
                                        @Param("yggh")String yggh);
}
