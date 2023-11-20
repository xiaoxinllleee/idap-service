package org.cmms.modules.ckjkpt.jcyj.ckyebd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.ckjkpt.jcyj.ckyebd.entity.CkjkptCkyebd;
import org.cmms.modules.ckjkpt.jcyj.ckyebd.mapper.CkjkptCkyebdMapper;
import org.cmms.modules.ckjkpt.jcyj.ckyebd.service.ICkjkptCkyebdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
@Service
public class CkjkptCkyebdServiceImpl extends ServiceImpl<CkjkptCkyebdMapper, CkjkptCkyebd> implements ICkjkptCkyebdService {
    @Autowired
    private CkjkptCkyebdMapper ckjkptZhckpldglMapper;
    @Override
    public void extract(Map<String,String> sql) {
        ckjkptZhckpldglMapper.extract(sql);
    }
}
