package org.cmms.modules.tjfx.xdgtzyb1.qhxdgt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.tjfx.xdgtzyb1.qhxdgt.entity.Qhxdgt;
import org.cmms.modules.tjfx.xdgtzyb1.qhxdgt.mapper.QhxdgtMapper;
import org.cmms.modules.tjfx.xdgtzyb1.qhxdgt.service.IQhxdgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-16
 * @Version: V1.0
 */
@Service
public class QhxdgtServiceImpl extends ServiceImpl<QhxdgtMapper, Qhxdgt> implements IQhxdgtService {
    @Autowired
    private QhxdgtMapper tjfxNshbeMapper;


    @Override
    public void extract(String ksrq,String jsrq,String zzbz) {
        tjfxNshbeMapper.extract(ksrq,jsrq,zzbz);
    }
}
