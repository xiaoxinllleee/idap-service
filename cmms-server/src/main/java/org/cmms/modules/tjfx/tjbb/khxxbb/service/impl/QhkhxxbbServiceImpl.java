package org.cmms.modules.tjfx.tjbb.khxxbb.service.impl;

import org.cmms.modules.tjfx.tjbb.khxxbb.entity.Qhkhxxbb;
import org.cmms.modules.tjfx.tjbb.khxxbb.mapper.QhkhxxbbMapper;
import org.cmms.modules.tjfx.tjbb.khxxbb.service.IQhkhxxbbService;
import org.cmms.modules.tjfx.xdgtzyb.qhxtgtzyb2.mapper.Qhxdgtzyb2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户信息报表(全行)
 * @Author: jeecg-boot
 * @Date:   2020-03-30
 * @Version: V1.0
 */
@Service
public class QhkhxxbbServiceImpl extends ServiceImpl<QhkhxxbbMapper, Qhkhxxbb> implements IQhkhxxbbService {
    @Autowired
    private QhkhxxbbMapper qhkhxxbbMapper;


    @Override
    public void extract(String tjyf) {
        qhkhxxbbMapper.extract(tjyf);
    }

}
