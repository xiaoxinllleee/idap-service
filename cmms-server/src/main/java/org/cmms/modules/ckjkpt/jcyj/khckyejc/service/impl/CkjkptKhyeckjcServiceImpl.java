package org.cmms.modules.ckjkpt.jcyj.khckyejc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.ckjkpt.jcyj.khckyejc.entity.CkjkptKhyeckjc;
import org.cmms.modules.ckjkpt.jcyj.khckyejc.mapper.CkjkptKhyeckjcMapper;
import org.cmms.modules.ckjkpt.jcyj.khckyejc.service.ICkjkptKhyeckjcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
@Service
public class CkjkptKhyeckjcServiceImpl extends ServiceImpl<CkjkptKhyeckjcMapper, CkjkptKhyeckjc> implements ICkjkptKhyeckjcService {
    @Autowired
    private CkjkptKhyeckjcMapper ckjkptZhckpldglMapper;
    @Override
    public void extract(String tjyf) {
        ckjkptZhckpldglMapper.extract(tjyf);
    }

    @Override
    public String getlvBytsM(String date) {return baseMapper.getlvBytsM(date); }

    @Override
    public String getlvBytsY(String date) {
        return baseMapper.getlvBytsY(date);
    }

    @Override
    public String getlvSytsM(String date) {
        return baseMapper.getlvSytsM(date);
    }

    @Override
    public String getlvSytsY(String date) {
        return baseMapper.getlvSytsY(date);
    }
}
