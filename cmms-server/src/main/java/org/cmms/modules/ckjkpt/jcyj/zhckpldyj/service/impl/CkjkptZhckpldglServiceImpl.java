package org.cmms.modules.ckjkpt.jcyj.zhckpldyj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.ckjkpt.jcyj.zhckpldyj.entity.CkjkptZhckpldgl;
import org.cmms.modules.ckjkpt.jcyj.zhckpldyj.mapper.CkjkptZhckpldglMapper;
import org.cmms.modules.ckjkpt.jcyj.zhckpldyj.service.ICkjkptZhckpldglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
@Service
public class CkjkptZhckpldglServiceImpl extends ServiceImpl<CkjkptZhckpldglMapper, CkjkptZhckpldgl> implements ICkjkptZhckpldglService {
    @Autowired
    private CkjkptZhckpldglMapper ckjkptZhckpldglMapper;
    @Override
    public void extract(String tjyf) {
        ckjkptZhckpldglMapper.extract(tjyf);
    }
}
