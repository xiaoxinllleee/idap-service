package org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqcklsjk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqcklsjk.entity.CkjkptTdrqcklsjk;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqcklsjk.mapper.CkjkptTdrqcklsjkMapper;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqcklsjk.service.ICkjkptTdrqcklsjkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-10-10
 * @Version: V1.0
 */
@Service
public class CkjkptTdrqcklsjkServiceImpl extends ServiceImpl<CkjkptTdrqcklsjkMapper, CkjkptTdrqcklsjk> implements ICkjkptTdrqcklsjkService {
    @Autowired
    private CkjkptTdrqcklsjkMapper ckjkptZhckpldglMapper;
    @Override
    public void extract(String tjyf) {
        ckjkptZhckpldglMapper.extract(tjyf);
    }
}
