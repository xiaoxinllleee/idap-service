package org.cmms.modules.tjfx.xdgtzyb.qhxtgtzyb2.service.impl;

import org.cmms.modules.tjfx.xdgtzyb.qhxtgtzyb2.entity.Qhxdgtzyb2;
import org.cmms.modules.tjfx.xdgtzyb.qhxtgtzyb2.mapper.Qhxdgtzyb2Mapper;
import org.cmms.modules.tjfx.xdgtzyb.qhxtgtzyb2.service.IQhxdgtzyb2Service;
import org.cmms.modules.tjfx.xdgtzytjbb.nshbe.mapper.TjfxNshbeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 全行行动挂图作业表2
 * @Author: jeecg-boot
 * @Date:   2020-03-13
 * @Version: V1.0
 */
@Service
public class Qhxdgtzyb2ServiceImpl extends ServiceImpl<Qhxdgtzyb2Mapper, Qhxdgtzyb2> implements IQhxdgtzyb2Service {

    @Autowired
    private Qhxdgtzyb2Mapper tjfxNshbeMapper;


    @Override
    public void extract(String tjyf) {
        tjfxNshbeMapper.extract(tjyf);
    }
}
