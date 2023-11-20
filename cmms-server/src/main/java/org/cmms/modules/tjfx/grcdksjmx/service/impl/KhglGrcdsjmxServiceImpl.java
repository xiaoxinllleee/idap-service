package org.cmms.modules.tjfx.grcdksjmx.service.impl;

import org.cmms.modules.tjfx.grcdksjmx.entity.KhglGrcdsjmx;
import org.cmms.modules.tjfx.grcdksjmx.mapper.KhglGrcdsjmxMapper;
import org.cmms.modules.tjfx.grcdksjmx.service.IKhglGrcdsjmxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * @Description: 个人存贷款数据明细
 * @Author: jeecg-boot
 * @Date:   2020-11-28
 * @Version: V1.0
 */
@Service
public class KhglGrcdsjmxServiceImpl extends ServiceImpl<KhglGrcdsjmxMapper, KhglGrcdsjmx> implements IKhglGrcdsjmxService {
    @Autowired
    KhglGrcdsjmxMapper khglGrcdsjmxMapper;

    @Override
    public void init(Map<String,Object> sql){
        khglGrcdsjmxMapper.init(sql);
    }


    @Override
    public List<Map> getgrjynck(String zjhm){
        return khglGrcdsjmxMapper.getgrjynck(zjhm);
    }

    @Override
    public String getCsz(String csbm) {
        return khglGrcdsjmxMapper.getCsz(csbm);
    }
}
