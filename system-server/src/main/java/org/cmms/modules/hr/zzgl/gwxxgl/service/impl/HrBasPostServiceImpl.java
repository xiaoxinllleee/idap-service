package org.cmms.modules.hr.zzgl.gwxxgl.service.impl;

import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasOranizationVo;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasPost;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasStaffPostVo;
import org.cmms.modules.hr.zzgl.gwxxgl.mapper.HrBasPostMapper;
import org.cmms.modules.hr.zzgl.gwxxgl.service.IHrBasPostService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

/**
 * @Description: 岗位信息管理
 * @Author: jeecg-boot
 * @Date:   2020-11-27
 * @Version: V1.0
 */
@Service
public class HrBasPostServiceImpl extends ServiceImpl<HrBasPostMapper, HrBasPost> implements IHrBasPostService {

    @Override
    public String queryByGwbz(String gwbz) {
        return baseMapper.queryByGwbz(gwbz);
    }

    @Override
    public List<HrBasStaffPostVo> getListClaim(String ywjgdm, String rglx, String gwbz, String khjlbz, String yggh) {
        return baseMapper.getListClaim(ywjgdm,rglx,gwbz,khjlbz,yggh);
    }

    @Override
    public List<HrBasStaffPostVo> getYggwxx(String yggh, String gyh, String khjlbz) {
        return baseMapper.getYggwxx(yggh, gyh, khjlbz);
    }
    @Override
    public List<HrBasStaffPostVo> getListFindBack(String jgdm) {
        return baseMapper.getListFindBack(jgdm);
    }

    @Override
    public List<HrBasOranizationVo> ygghInfo(String ywjgdm, String zzbz) {
        return baseMapper.ygghInfo(ywjgdm,zzbz);
    }

    @Override
    public List<HrBasStaffPostVo> getYggw(String yggh, String tjrq) {
        return baseMapper.getYggw(yggh,tjrq);
    }
}
