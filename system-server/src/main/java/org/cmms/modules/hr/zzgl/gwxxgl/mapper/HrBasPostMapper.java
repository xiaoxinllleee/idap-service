package org.cmms.modules.hr.zzgl.gwxxgl.mapper;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasOranizationVo;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasPost;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasStaffPostVo;

import java.util.Date;
import java.util.List;

/**
 * @Description: 岗位信息管理
 * @Author: jeecg-boot
 * @Date:   2020-11-27
 * @Version: V1.0
 */
public interface HrBasPostMapper extends BaseMapper<HrBasPost> {

    public String queryByGwbz(String gwbz);

    List<HrBasStaffPostVo> getListClaim(@Param("ywjgdm")String ywjgdm, @Param("rglx")String rglx,
                                        @Param("gwbz")String gwbz, @Param("khjlbz")String khjlbz,
                                        @Param("yggh")String yggh);


    List<HrBasStaffPostVo> getYggwxx(@Param("yggh")String yggh, @Param("gyh")String gyh, @Param("khjlbz")String khjlbz);


    public List<HrBasStaffPostVo> getListFindBack(String jgdm);

    List<HrBasOranizationVo> ygghInfo(@Param("ywjgdm")String ywjgdm, @Param("zzbz")String zzbz);

    List<HrBasStaffPostVo> getYggw(@Param("yggh")String yggh,@Param("tjrq") String tjrq);

}
