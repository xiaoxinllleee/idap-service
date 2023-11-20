package org.cmms.modules.tjfx.tjbb.khqzywbb.service.impl;

import org.cmms.modules.tjfx.tjbb.khqzywbb.entity.Qhkhqzywbb;
import org.cmms.modules.tjfx.tjbb.khqzywbb.mapper.QhkhqzywbbMapper;
import org.cmms.modules.tjfx.tjbb.khqzywbb.service.IQhkhqzywbbService;
import org.cmms.modules.tjfx.tjbb.khywbb.mapper.QhkhywbbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户潜在业务报表(全行)
 * @Author: jeecg-boot
 * @Date:   2020-04-01
 * @Version: V1.0
 */
@Service
public class QhkhqzywbbServiceImpl extends ServiceImpl<QhkhqzywbbMapper, Qhkhqzywbb> implements IQhkhqzywbbService {
    @Autowired
    private QhkhqzywbbMapper qhkhqzywbbMapper;


    @Override
    public void extract() {
        qhkhqzywbbMapper.extract();
    }
}
