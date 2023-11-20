package org.cmms.modules.hr.yggl.ygxxgl.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.hr.yggl.ygxxgl.entity.Vhrbasstaffpost;
import org.cmms.modules.hr.yggl.ygxxgl.mapper.VhrbasstaffpostMapper;
import org.cmms.modules.hr.yggl.ygxxgl.service.IVhrbasstaffpostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 岗位表
 * @Author: jeecg-boot
 * @Date:   2020-08-08
 * @Version: V1.0
 */
@Service

public class VhrbasstaffpostServiceImpl extends ServiceImpl<VhrbasstaffpostMapper, Vhrbasstaffpost> implements IVhrbasstaffpostService {
    @Override
    public Vhrbasstaffpost selectByYggh(String yggh) {
        return baseMapper.selectByYggh(yggh);
    }

    @Override
    public Vhrbasstaffpost selectYgList(String yggh, String zzbz) {
        return baseMapper.selectYgList(yggh,zzbz);
    }

    @Override
    public Vhrbasstaffpost selectYgByLrsj(String yggh, String lrsj) {
        return baseMapper.selectYgByLrsj(yggh,lrsj);
    }

    @Override
    public List<Vhrbasstaffpost> geYgxxByZzbz(String zzbz) {
        return baseMapper.geYgxxByZzbz(zzbz);
    }

    @Override
    public List<Vhrbasstaffpost> getZhYgxxByZzbz(String zzbz) {
        return baseMapper.getZhYgxxByZzbz(zzbz);
    }
}
