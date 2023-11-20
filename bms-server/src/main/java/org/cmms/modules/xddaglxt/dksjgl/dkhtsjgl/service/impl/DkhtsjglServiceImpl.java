package org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.service.impl;

import org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.entity.Dkhtsjgl;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.entity.HrBasStaffPostVo;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.mapper.DkhtsjglMapper;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.service.IDkhtsjglService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 贷款合同数据管理
 * @Author: jeecg-boot
 * @Date:   2021-11-24
 * @Version: V1.0
 */
@Service
public class DkhtsjglServiceImpl extends ServiceImpl<DkhtsjglMapper, Dkhtsjgl> implements IDkhtsjglService {

    @Override
    public void pDkhtsjgl() {
        baseMapper.pDkhtsjgl();
    }

    @Override
    public List<HrBasStaffPostVo> getListClaim(String ywjgdm, String rglx, String gwbz, String khjlbz, String yggh) {
        return baseMapper.getListClaim(ywjgdm, rglx, gwbz, khjlbz, yggh);
    }
}
