package org.cmms.modules.ckjkpt.jcyj.debdcx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.ckjkpt.jcyj.debdcx.entity.CkjkptDebdcx;
import org.cmms.modules.ckjkpt.jcyj.debdcx.mapper.CkjkptDebdcxMapper;
import org.cmms.modules.ckjkpt.jcyj.debdcx.service.ICkjkptDebdcxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-10-10
 * @Version: V1.0
 */
@Service
public class CkjkptDebdcxServiceImpl extends ServiceImpl<CkjkptDebdcxMapper, CkjkptDebdcx> implements ICkjkptDebdcxService {
    @Autowired
    private CkjkptDebdcxMapper ckjkptZhckpldglMapper;
    @Override
    public void extract(String tjyf) {
        ckjkptZhckpldglMapper.extract(tjyf);
    }
}
