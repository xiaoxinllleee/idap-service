package org.cmms.modules.dkjkpt.cssz.dqqkjccssz.service.impl;

import org.cmms.modules.dkjkpt.cssz.dkqxcssz.entity.Dkjkpt_dkqxcssz;
import org.cmms.modules.dkjkpt.cssz.dqqkjccssz.entity.Dkjkpt_dqqkjccssz;
import org.cmms.modules.dkjkpt.cssz.dqqkjccssz.mapper.Dkjkpt_dqqkjccsszMapper;
import org.cmms.modules.dkjkpt.cssz.dqqkjccssz.service.IDkjkpt_dqqkjccsszService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 到期情况监测参数设置
 * @Author: jeecg-boot
 * @Date:   2020-11-06
 * @Version: V1.0
 */
@Service
public class Dkjkpt_dqqkjccsszServiceImpl extends ServiceImpl<Dkjkpt_dqqkjccsszMapper, Dkjkpt_dqqkjccssz> implements IDkjkpt_dqqkjccsszService {

    @Autowired
    private Dkjkpt_dqqkjccsszMapper dkjkpt_dqqkjccsszMapper;
    @Override
    public Integer deleteByCsbh(String csbh) {
        return dkjkpt_dqqkjccsszMapper.deleteByCsbh(csbh);
    }

    @Override
    public Integer updateByCsbh(Dkjkpt_dkqxcssz cs) {
        return dkjkpt_dqqkjccsszMapper.updateByCsbh(cs);

    }
}
