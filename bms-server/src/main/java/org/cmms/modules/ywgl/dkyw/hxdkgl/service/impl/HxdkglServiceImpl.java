package org.cmms.modules.ywgl.dkyw.hxdkgl.service.impl;

import org.cmms.modules.ywgl.dkyw.hxdkgl.entity.HrBasStaffPostVo;
import org.cmms.modules.ywgl.dkyw.hxdkgl.entity.Hxdkgl;
import org.cmms.modules.ywgl.dkyw.hxdkgl.mapper.HxdkglMapper;
import org.cmms.modules.ywgl.dkyw.hxdkgl.service.IHxdkglService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 核销贷款管理
 * @Author: jeecg-boot
 * @Date:   2021-09-23
 * @Version: V1.0
 */
@Service
public class HxdkglServiceImpl extends ServiceImpl<HxdkglMapper, Hxdkgl> implements IHxdkglService {

    @Override
    public List<HrBasStaffPostVo> getListClaim(String gyh, String khjlbz, String yggh) {
        return baseMapper.getListClaim(gyh, khjlbz, yggh);
    }
}
