package org.cmms.modules.tjfx.khzftj.service.impl;

import org.cmms.modules.tjfx.khzftj.entity.Khzftj;
import org.cmms.modules.tjfx.khzftj.mapper.KhzftjMapper;
import org.cmms.modules.tjfx.khzftj.service.IKhzftjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 客户走访统计
 * @Author: cmms
 * @Date:   2019-09-19
 * @Version: V1.0
 */
@Service
public class KhzftjServiceImpl extends ServiceImpl<KhzftjMapper, Khzftj> implements IKhzftjService {

    @Autowired
    private KhzftjMapper khzftjMapper;

    @Override
    @Transactional
    public void extract(String tjyf){
        khzftjMapper.extract(tjyf);
    }


}
