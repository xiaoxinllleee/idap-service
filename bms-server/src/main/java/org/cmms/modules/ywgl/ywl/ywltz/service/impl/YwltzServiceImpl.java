package org.cmms.modules.ywgl.ywl.ywltz.service.impl;

import org.cmms.modules.ywgl.ywl.ywltz.entity.HrBasStaffPostVo;
import org.cmms.modules.ywgl.ywl.ywltz.entity.Ywltz;
import org.cmms.modules.ywgl.ywl.ywltz.mapper.YwltzMapper;
import org.cmms.modules.ywgl.ywl.ywltz.service.IYwltzService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 业务量调整
 * @Author: jeecg-boot
 * @Date:   2021-09-29
 * @Version: V1.0
 */
@Service
public class YwltzServiceImpl extends ServiceImpl<YwltzMapper, Ywltz> implements IYwltzService {

    @Override
    public List<HrBasStaffPostVo> getListClaim(String ywjgdm, String rglx, String gwbz, String khjlbz, String yggh) {
        return baseMapper.getListClaim(ywjgdm,rglx,gwbz,khjlbz,yggh);
    }
}
