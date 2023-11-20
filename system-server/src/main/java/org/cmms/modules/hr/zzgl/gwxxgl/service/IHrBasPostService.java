package org.cmms.modules.hr.zzgl.gwxxgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasOranizationVo;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasPost;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasStaffPostVo;

import java.util.Date;
import java.util.List;

/**
 * @Description: 岗位信息管理
 * @Author: jeecg-boot
 * @Date:   2020-11-27
 * @Version: V1.0
 */
@DS("eweb")
public interface IHrBasPostService extends IService<HrBasPost> {
    public String queryByGwbz(String gwbz);

    List<HrBasStaffPostVo> getListClaim(String ywjgdm, String rglx, String gwbz, String khjlbz, String yggh);

    List<HrBasStaffPostVo> getYggwxx(String yggh, String gyh, String khjlbz);

    List<HrBasStaffPostVo> getListFindBack(String jgdm);

    /**
     * 判断只能添加员工所在支行
     * @param ywjgdm
     * @param zzbz
     * @return
     */
    List<HrBasOranizationVo> ygghInfo(@Param("ywjgdm")String ywjgdm, @Param("zzbz")String zzbz);

    List<HrBasStaffPostVo> getYggw(String yggh, String tjrq);
}
