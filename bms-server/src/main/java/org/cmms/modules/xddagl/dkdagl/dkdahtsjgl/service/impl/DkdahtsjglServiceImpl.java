package org.cmms.modules.xddagl.dkdagl.dkdahtsjgl.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.xddagl.dkdagl.dkdahtsjgl.entity.Dkdahtsjgl;
import org.cmms.modules.xddagl.dkdagl.dkdahtsjgl.mapper.DkdahtsjglMapper;
import org.cmms.modules.xddagl.dkdagl.dkdahtsjgl.service.IDkdahtsjglService;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.entity.HrBasStaffPostVo;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 贷款合同数据管理
 * @Author: jeecg-boot
 * @Date:   2022-01-07
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class DkdahtsjglServiceImpl extends ServiceImpl<DkdahtsjglMapper, Dkdahtsjgl> implements IDkdahtsjglService {

    @Override
    public void pDkhtsjgl() {
        baseMapper.pDkhtsjgl();
    }

    @Override
    public List<HrBasStaffPostVo> getListClaim(String ywjgdm, String rglx, String gwbz, String khjlbz, String yggh) {
        return baseMapper.getListClaim(ywjgdm, rglx, gwbz, khjlbz, yggh);
    }
}
