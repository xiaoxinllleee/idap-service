package org.cmms.modules.khlc.grgpgl.service.impl;

import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasStaffPostVo;
import org.cmms.modules.khlc.grgpgl.entity.Grgpgl;
import org.cmms.modules.khlc.grgpgl.mapper.GrgpglMapper;
import org.cmms.modules.khlc.grgpgl.service.IGrgpglService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 个人挂片管理
 * @Author: jeecg-boot
 * @Date:   2023-03-09
 * @Version: V1.0
 */
@Service
public class GrgpglServiceImpl extends ServiceImpl<GrgpglMapper, Grgpgl> implements IGrgpglService {

    @Override
    public List<HrBasStaffPostVo> getListClaim(String zzbz, String yggh, String ygxm) {
        return baseMapper.getListClaim(zzbz, yggh, ygxm);
    }
}
