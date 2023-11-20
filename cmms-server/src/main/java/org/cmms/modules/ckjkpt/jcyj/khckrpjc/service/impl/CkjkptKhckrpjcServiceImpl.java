package org.cmms.modules.ckjkpt.jcyj.khckrpjc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.ckjkpt.jcyj.khckrpjc.entity.CkjkptKhckrpjc;
import org.cmms.modules.ckjkpt.jcyj.khckrpjc.mapper.CkjkptKhckrpjcMapper;
import org.cmms.modules.ckjkpt.jcyj.khckrpjc.service.ICkjkptKhckrpjcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
@Service
public class CkjkptKhckrpjcServiceImpl extends ServiceImpl<CkjkptKhckrpjcMapper, CkjkptKhckrpjc> implements ICkjkptKhckrpjcService {
    @Autowired
    private CkjkptKhckrpjcMapper ckjkptZhckpldglMapper;
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
