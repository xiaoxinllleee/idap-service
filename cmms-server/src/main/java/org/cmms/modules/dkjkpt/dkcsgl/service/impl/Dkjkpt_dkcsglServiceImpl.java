package org.cmms.modules.dkjkpt.dkcsgl.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.dkjkpt.dkcsgl.entity.Dkjkpt_dkcsgl;
import org.cmms.modules.dkjkpt.dkcsgl.mapper.Dkjkpt_dkcsglMapper;
import org.cmms.modules.dkjkpt.dkcsgl.service.IDkjkpt_dkcsglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-12-18
 * @Version: V1.0
 */
@Service
public class Dkjkpt_dkcsglServiceImpl extends ServiceImpl<Dkjkpt_dkcsglMapper, Dkjkpt_dkcsgl> implements IDkjkpt_dkcsglService {

    @Autowired
    private Dkjkpt_dkcsglMapper dkjkpt_dkcsglMapper;
    @Override
    public void extract() {
        dkjkpt_dkcsglMapper.extract();
    }
}
