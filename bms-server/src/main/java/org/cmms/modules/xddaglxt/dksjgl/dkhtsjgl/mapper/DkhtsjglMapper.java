package org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.entity.Dkhtsjgl;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.entity.HrBasStaffPostVo;

/**
 * @Description: 贷款合同数据管理
 * @Author: jeecg-boot
 * @Date:   2021-11-24
 * @Version: V1.0
 */
public interface DkhtsjglMapper extends BaseMapper<Dkhtsjgl> {
    void pDkhtsjgl();

    List<HrBasStaffPostVo> getListClaim(@Param("ywjgdm")String ywjgdm, @Param("rglx")String rglx,
                                        @Param("gwbz")String gwbz, @Param("khjlbz")String khjlbz,
                                        @Param("yggh")String yggh);
}
