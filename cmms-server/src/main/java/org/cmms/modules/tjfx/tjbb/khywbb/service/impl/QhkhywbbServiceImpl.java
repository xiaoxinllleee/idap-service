package org.cmms.modules.tjfx.tjbb.khywbb.service.impl;

import org.cmms.modules.tjfx.tjbb.khxxbb.mapper.QhkhxxbbMapper;
import org.cmms.modules.tjfx.tjbb.khywbb.entity.Qhkhywbb;
import org.cmms.modules.tjfx.tjbb.khywbb.mapper.QhkhywbbMapper;
import org.cmms.modules.tjfx.tjbb.khywbb.service.IQhkhywbbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户业务报表(全行)
 * @Author: jeecg-boot
 * @Date:   2020-03-30
 * @Version: V1.0
 */
@Service
public class QhkhywbbServiceImpl extends ServiceImpl<QhkhywbbMapper, Qhkhywbb> implements IQhkhywbbService {
    @Autowired
    private QhkhywbbMapper qhkhywbbMapper;


    @Override
    public void extract() {
        qhkhywbbMapper.extract();
    }

    @Override
    public void extractRC(String zxrkrq) {
        qhkhywbbMapper.extractRC(zxrkrq);
    }
}
