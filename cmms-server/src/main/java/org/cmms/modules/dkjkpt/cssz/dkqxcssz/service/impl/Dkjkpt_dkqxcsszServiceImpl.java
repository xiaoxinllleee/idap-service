package org.cmms.modules.dkjkpt.cssz.dkqxcssz.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.cssz.dkqxcssz.entity.Dkjkpt_dkqxcssz;
import org.cmms.modules.dkjkpt.cssz.dkqxcssz.mapper.Dkjkpt_dkqxcsszMapper;
import org.cmms.modules.dkjkpt.cssz.dkqxcssz.service.IDkjkpt_dkqxcsszService;
import org.cmms.modules.dkjkpt.dksjjk.dkjefc.dkjefc_zh.mapper.Dkjkpt_zhdkjefcMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷款期限参数设置
 * @Author: jeecg-boot
 * @Date: 2020-11-06
 * @Version: V1.0
 */
@Service
public class Dkjkpt_dkqxcsszServiceImpl extends ServiceImpl<Dkjkpt_dkqxcsszMapper, Dkjkpt_dkqxcssz> implements IDkjkpt_dkqxcsszService {

    @Autowired
    private Dkjkpt_dkqxcsszMapper dkjkpt_dkqxcsszMapper;

    @Override
    public Dkjkpt_dkqxcssz selectByCsbh(String csbh) {
        return dkjkpt_dkqxcsszMapper.selectByCsbh(csbh);
    }

    @Override
    public Integer deleteByCsbh(String csbh) {
        return dkjkpt_dkqxcsszMapper.deleteByCsbh(csbh);
    }

    @Override
    public Integer updateByCsbh(Dkjkpt_dkqxcssz cs) {
        return dkjkpt_dkqxcsszMapper.updateByCsbh(cs);

    }
}
